package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import org.usfirst.frc.team3863.robot.RobotMap;


/**
 *
 */
public class PneumaticsCompressor extends Subsystem {
	Compressor comp = new Compressor(RobotMap.PCM_ID);

    public void initDefaultCommand() {
        //setDefaultCommand(new enableCompressor());
    }
    
    public void enableCompressor() {
    	comp.setClosedLoopControl(true);
    	
    }
    
    public void disableCompressor() {
    	comp.setClosedLoopControl(false);

    	
    }
}

