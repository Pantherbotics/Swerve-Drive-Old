package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.AnalogInput;


public class SwerveModule{
    private AnalogInput SteeringAnalog = new AnalogInput(0);
    private TalonSRX mDrive, mSteering;
    private Notifier pidLoop;           //A notifier is a thread. Basically think of a thread as something running in the background.
    private volatile double setpoint, sumError, errorChange, lastError, currentError, pidOutput;
    private boolean isReversed;

    private static final double dt = 0.01;  //this is how fast we run our PID loop.
    private static final double ROTATION_SENSOR_MIN = 156;  //we measured this
    private static final double ROTATION_SENSOR_MAX = 978;  //and this
    
    public SwerveModule(int kSteeringID, int kDriveID, boolean isReversed, double kP, double kI, double kD){
        mDrive = new TalonSRX(kDriveID);
        mSteering = new TalonSRX(kSteeringID);
        mSteering.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 1, 0);
        mSteering.setInverted(false);
        mSteering.setSensorPhase(true);
        mDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        sumError = 0;
        lastError = getModifiedError();
        currentError = lastError;
    
        pidLoop = new Notifier(() -> {
            currentError = getModifiedError();  //update the current error to the most recent one
            sumError += currentError;
            errorChange = lastError - currentError;
            pidOutput = kP * currentError + kI * sumError * dt - kD * errorChange/dt; //you guys know this, or at least you better...
            mSteering.set(ControlMode.PercentOutput, pidOutput);
            lastError = currentError;   //update the last error to be the current error
        });

        this.isReversed = isReversed;

        pidLoop.startPeriodic(dt);
    }
    public int getDriveEncoder(){
        return (mDrive.getSelectedSensorVelocity(0));
    }
    
    public double getSteeringRadians(){
        return (((Math.abs(mSteering.getSelectedSensorPosition(0) % 1024)) - ROTATION_SENSOR_MIN) * (2.0*Math.PI/(ROTATION_SENSOR_MAX-ROTATION_SENSOR_MIN))) - Math.PI; //this is actually pretty good... the only thing I changed was to make everything into degrees.
    }

    public double getSteeringOutput(){
        return pidOutput;
    }
    public double getSteeringDegrees(){
        return Math.toDegrees(getSteeringRadians());
    }

    public double getError(){
        return setpoint - getSteeringRadians();
    }

    public double getModifiedError(){
        return boundHalfRadians(getError());
    }

    public void setDrivePower(double power){
        if(isReversed)
            mDrive.set(ControlMode.PercentOutput, -power);
        else
            mDrive.set(ControlMode.PercentOutput, power);
    }

    public void setSteeringDegrees(double deg){
        setpoint = Math.toRadians(deg);
    }

    public void setSteeringRadians(double rad){
        setpoint = rad;
    }

    public double getSetpointRadians(){
        return setpoint;
    }

    public double getSetpointDegrees(){
        return Math.toDegrees(setpoint);
    }

    private double boundHalfRadians(double radians){
        while(radians >= Math.PI) radians -=2*Math.PI;
        while(radians < -Math.PI) radians +=2*Math.PI;
        return radians;
    }
}
