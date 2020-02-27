/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
//Imports for the sticks of which will be goated on
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  //Declarations for the sticks of which will be goated on
  public final static Joystick m_joystick = new Joystick(Constants.JOYSTICK_PORT);
  public final static XboxController m_xboxSticks = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  
  public final static Drivetrain m_drivetrain = new Drivetrain();
  public final static Limelight m_limelight = new Limelight();
  public final static Spinner m_spinner = new Spinner();
  public final static Transport m_transport = new Transport();
  public final static Shooter m_shooter = new Shooter(); 
  public final static Lifter m_lifter = new Lifter();
  public final static Intake m_intake = new Intake();

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  public final static DriveWithSticks m_drive = new DriveWithSticks();
  public final static Autonomous m_auto = new Autonomous();
  public final static AimBot m_aimBot = new AimBot(m_limelight);
  public final static SpinFortuneWheelThree m_spinFortuneWheelThree = new SpinFortuneWheelThree(m_spinner);
  public final static FortuneWheelBot m_fortuneWheelBot = new FortuneWheelBot(m_spinner);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_auto;
  }
}
