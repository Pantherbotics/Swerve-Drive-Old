package team3863.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

    public static double WHEEL_BASE = 30;   //in
    public static double TRACK_WIDTH = 30;  //in
    public static double RADIUS = Math.sqrt(Math.pow(WHEEL_BASE, 2) + Math.pow(TRACK_WIDTH, 2));

    public static int topRight_Drive = 7;
    public static int topRight_Steer = 12;
    public static int topLeft_Drive = 2;
    public static int topLeft_Steer = 15;
    public static int botRight_Drive = 1;
    public static int botRight_Steer = 3;
    public static int botLeft_Drive = 4;
    public static int botLeft_Steer = 5;

    public static int topRight_Offset = 90;
    public static int topLeft_Offset = 45;
    public static int botRight_Offset = 45;
    public static int botLeft_Offset = 45;
    
    public static int PCM_ID = 6;

    public static double steer_P = 0.6;
    /*
        when the error is 1120, the throttle will be 100%
        the p constant is calculated by (1.0*1023)/1120
        where 1.0 = throttle when error is 1120
        1120 = 4x encoder counts
        1023 = max talon output
     */
    public static double steer_I = 0.0005;
    public static double steer_D = 0.0;

    public static int shift_Forward = 0;
    public static int shift_Reverse = 1;

    public static int leftJoy = 0;
    public static int rightJoy = 1;

    public static SPI.Port gyro_port = SPI.Port.kOnboardCS0;
}
