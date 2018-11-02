package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.AnalogInput;


public class SwerveModule{
    //private AnalogInput SteeringAnalog = new AnalogInput(0);
    private TalonSRX mDrive, mSteering;
    private Notifier pidLoop;           //A notifier is a thread. Basically think of a thread as something running in the background.
    private volatile double setpoint, sumError, errorChange, lastError, currentError, pidOutput;
    private boolean isReversed;

    private static final double dt = 0.01;  //this is how fast we run our PID loop.
    private static final double ROTATION_SENSOR_MIN = 156;  //we measured this
    private static final double ROTATION_SENSOR_MAX = 978;  //and this
    
    /**
     * 
     * @param kSteeringID   the ID of the steering motor
     * @param kDriveID      the ID of the drive motor
     * @param isReversed    if the module is physically reversed on the robot
     * @param kP            the steering kP gain
     * @param kI            the steering kI gain
     * @param kD            the steering kD gain
     */
    public SwerveModule(int kSteeringID, int kDriveID, boolean isReversed, double kP, double kI, double kD){
        mDrive = new TalonSRX(kDriveID);
        mSteering = new TalonSRX(kSteeringID);

        //Configure steering Talon SRX
        mSteering.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        mSteering.configOpenloopRamp(0, Constants.kTimeoutMs);      //this is what we were missing!
        mSteering.configPeakCurrentDuration(Constants.kPeakCurrentDuration, Constants.kTimeoutMs);
        mSteering.configPeakCurrentLimit(Constants.kPeakCurrentLimit, Constants.kTimeoutMs);
        mSteering.configContinuousCurrentLimit(Constants.kSustainedCurrentLimit, Constants.kTimeoutMs);
        mSteering.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 1, 0);
        mSteering.setInverted(false);
        mSteering.setSensorPhase(true);

        //Configure drive Talon SRX
        mDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        sumError = 0;
        lastError = getModifiedError();
        currentError = lastError;
    
        pidLoop = new Notifier(() -> {
            currentError = getModifiedError();  //update the current error to the most recent one
            sumError += currentError * dt;
            errorChange = (currentError-lastError)/dt;
            pidOutput = kP * currentError + kI * sumError + kD * errorChange; //you guys know this, or at least you better...
            mSteering.set(ControlMode.PercentOutput, pidOutput);
            lastError = currentError;   //update the last error to be the current error
        });

        this.isReversed = isReversed;

        pidLoop.startPeriodic(dt);
    }

    /**@return  the velocity of the wheel, measured in ticks/100ms
     * 
     */
    public int getDriveEncoder(){
        return (mDrive.getSelectedSensorVelocity(0));
    }

    /**
     * @return  the angle of the wheel, where angle is an element of [-pi, pi]
     */

    public double getSteeringRadians(){
        return (((Math.abs(mSteering.getSelectedSensorPosition(0) % 1024)) - ROTATION_SENSOR_MIN) * (2.0*Math.PI/(ROTATION_SENSOR_MAX-ROTATION_SENSOR_MIN))) - Math.PI; //this is actually pretty good... the only thing I changed was to make everything into degrees.
    }

    /**
     * 
     * @return  the closed-loop PID output, calculated by PID loop
     */
    public double getSteeringOutput(){
        return pidOutput;
    }

    /**
     * 
     * @return the angle of the wheel, where angle is an element of [-180, 180]
     */
    public double getSteeringDegrees(){
        return Math.toDegrees(getSteeringRadians());
    }

    /**
     * 
     * @return  the unbounded steering error, in radians
     */
    public double getError(){
        return setpoint - getSteeringRadians();
    }

    /**
     * 
     * @return  the steering error bounded to [-pi, pi]
     */
    public double getModifiedError(){
        return boundHalfRadians(getError());
    }

    /**
     * 
     * @param power the power of the wheel, where power is [-1.0, 1.0]
     */
    public void setDrivePower(double power){
        if(isReversed)
            mDrive.set(ControlMode.PercentOutput, -power);
        else
            mDrive.set(ControlMode.PercentOutput, power);
    }

    /**
     * 
     * @param deg   the angle to set the wheel to, in degrees
     */
    public void setSteeringDegrees(double deg){
        setpoint = Math.toRadians(deg);
    }

    /**
     * 
     * @param rad   the angle to set the wheel to, in radians
     */
    public void setSteeringRadians(double rad){
        setpoint = rad;
    }

    /**
     * 
     * @return returns the setpoint of the steering in radians
     */
    public double getSetpointRadians(){
        return setpoint;
    }

    /**
     * 
     * @return  returns the setpoint of the sttering in degrees
     */
    public double getSetpointDegrees(){
        return Math.toDegrees(setpoint);
    }

    /**
     * 
     * @param radians   an unbounded angle in radians
     * @return          a radian measure bounded between [-pi, pi]
     */
    private double boundHalfRadians(double radians){
        while(radians >= Math.PI) radians -=2*Math.PI;
        while(radians < -Math.PI) radians +=2*Math.PI;
        return radians;
    }
}
