package frc.robot;

public class Constants{
    //Motors
    public static int kDriveID = 1;
    public static int kSteeringID = 3;
    //Buttons/Joystick
    public static int kJoyStick = 0;
    public static int kJoystickLeft = 1;
    public static int kToggleDrive = 4;
    public static int kSetMotorSpeed = 0;

    //PID
    public static double kSwerveP = 0.8;
    public static double kSwerveI = 0.0;
    public static double kSwerveD = 0.0;

    public static int kPeakCurrentDuration = 10;
    public static int kPeakCurrentLimit = 20;
    public static int kSustainedCurrentLimit = 15;
    public static int kPIDLoopIdx = 0;
    public static int kTimeoutMs = 30;
}

