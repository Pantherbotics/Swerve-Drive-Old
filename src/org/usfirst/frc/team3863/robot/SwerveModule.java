package org.usfirst.frc.team3863.robot;

/**
 * Created by Aaron Fang on 10/3/2017.
 */
public interface SwerveModule {
    public void zeroRotation();
    public void setAngleDegrees(double angle);
    public void setWheelPower(double power);
}
