/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

import edu.wpi.first.wpilibj.I2C;

public final class Constants {

    //Drivetrain ports
    public static final int LB_DT_PORT = 0;
    public static final int RB_DT_PORT = 2;
    public static final int LF_DT_PORT = 1;
    public static final int RF_DT_PORT = 3;

    //Component ports 
    public static final int SPINNER_PORT = 4;
    public static final int INTAKE_PORT = 5;
    public static final int TRANSPORT_PORT = 6;
    public static final int LIFTER_PORT = 7;

    //Shooter Ports on Expansion Board
    public static final int R_SHOOTER_CAN_ID = 9;
    public static final int L_SHOORTER_CAN_ID = 11;

    //Ports for the sticks of which will be goated on
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int JOYSTICK_PORT = 1;
    
    //Encoder Rate 
    public static final double ENCODER_RATE = 1000;

    //Speeds
    public static final double TRANSPORT_SPEED = 0.3;
    public static final double FORTUNE_WHEEL_SPEED = 0.25;
    public static final double APPLY_PRESSURE_SPEED = 0.55;
    public static final double DT_SPIN_SPEED = 0.7;

    public static final I2C.Port i2cPort = I2C.Port.kOnboard;
}
