package frc.robot.Util;

public class DriveSignal {
    private double degrees, speed;
    public DriveSignal(double degrees, double speed){
        this.degrees = degrees;
        this.speed = speed;
    }

    public double getDegrees(){
        return degrees;
    }

    public double getSpeed(){
        return speed;
    }

    public void setDegrees(double degrees){
        this.degrees = degrees;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
}