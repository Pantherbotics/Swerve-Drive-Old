package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;

public class SwerveModule{
    private TalonSRX mDrive, mSteering;
    private Notifier pidLoop;           //A notifier is a thread. Basically think of a thread as something running in the background.
    private double setpoint, sumError, errorChange, lastError, currentError, pidOutput;
    private boolean isReversed;

    private static final double dt = 0.01;  //this is how fast we run our PID loop.
    private static final double ROTATION_SENSOR_MIN = 156;  //we measured this
    private static final double ROTATION_SENSOR_MAX = 822;  //and this
    
    public SwerveModule(int kSteeringID, int kDriveID, boolean isReversed, double kP, double kI, double kD){
        mDrive = new TalonSRX(kDriveID);
        mSteering = new TalonSRX(kSteeringID);
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
            //mSteering.set(ControlMode.PercentOutput, pidOutput);
            lastError = currentError;   //update the last error to be the current error
        });

        this.isReversed = isReversed;

        pidLoop.startPeriodic(dt);
        /*
        mSteering.configSetParameter(ParamEnum.eFeedbackNotContinuous, 0, 0x00, 0x00, 0x00);
        mSteering.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		mSteering.config_kP(Constants.kPIDLoopIdx, Constants.kSwerveP, Constants.kTimeoutMs);
		mSteering.config_kI(Constants.kPIDLoopIdx, Constants.kSwerveI, Constants.kTimeoutMs);
        mSteering.config_kD(Constants.kPIDLoopIdx, Constants.kSwerveD, Constants.kTimeoutMs);
        mSteering.configAllowableClosedloopError(30, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        */

        /** so basically we don't want to use the Talon SRX PID stuff for continuous rotation because it's garbage FOR THAT PURPOSE, since it doesn't handle
         * crossing that boundary condition very well. We can still use it for our velocity PID on our drive motor because in that case there is no discontinuous
         * jump for the TalonSRX to get tripped up on -AF
         */
    }
    public int getDriveEncoder(){
        return (mDrive.getSelectedSensorVelocity(0));
    }
    public double getSteeringRadians(){
        //return (mSteering.getSelectedSensorPosition(0));
        System.out.println(getSteeringRadians());
        return (((Math.abs(mSteering.getSelectedSensorPosition(0) % 1024)) - ROTATION_SENSOR_MIN) * (2*Math.PI/ROTATION_SENSOR_MAX)) - Math.PI; //this is actually pretty good... the only thing I changed was to make everything into degrees.
    }

    public double getSteeringDegrees(){
        return Math.toDegrees(getSteeringRadians());
    }

    public double getError(){
        return setpoint - getSteeringRadians();
    }

    public double getModifiedError(){
        return Math.sin(getError());
        //return getError();
    }

    public void setDrivePower(double power){
        if(isReversed)
            mDrive.set(ControlMode.PercentOutput, -power);
        else
            mDrive.set(ControlMode.PercentOutput, power);
    }
    public void setSteeringDegrees(double deg){
        setpoint = Math.toRadians(deg);
        /** 
        int steeringEncoder = getSteeringEncoder();
        if (steeringEncoder > 1023 || steeringEncoder < 0) {
            mSteering.setSelectedSensorPosition(Math.abs(getSteeringEncoder()%1023), Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        }
        mSteering.set(ControlMode.Position, pos);
        */
    }

    public void setSteeringRadians(double rad){
        setpoint = rad;
    }
}
