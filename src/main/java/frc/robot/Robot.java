/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  private final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  //TODOBOBOT
  //Test: Shoot -> Find Encoder Rate, Aimbot, Auto, 
  //Fix Spark Maxs
  //Write Documentation


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);

    SmartDashboard.putData("McKinley's Yeet Mode", new DriveWithSticks());
    SmartDashboard.putData("Aim Bot", new AimBot(RobotContainer.m_limelight));
    SmartDashboard.putData("Wheel of Fortune", new FortuneWheelBot(RobotContainer.m_spinner));

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Limelight X-Offset", RobotContainer.m_limelight.getXPos());
    SmartDashboard.putNumber("Limelight Y-Offset", RobotContainer.m_limelight.getYPos());
    SmartDashboard.putNumber("Limelight Area", RobotContainer.m_limelight.getImageArea());
    SmartDashboard.putNumber("Limelight Skew", RobotContainer.m_limelight.getSkew());
    SmartDashboard.putNumber("Limelight Latency", RobotContainer.m_limelight.getLatency());

    SmartDashboard.putBoolean("AimBot Status", RobotContainer.m_aimBot.isFinished());
    SmartDashboard.putNumber("Distance Adjust", RobotContainer.m_aimBot.distanceAdjust);
    SmartDashboard.putNumber("Left Drive Speed", RobotContainer.m_aimBot.leftDriveSpeed);
    SmartDashboard.putNumber("Right Drive Speed", RobotContainer.m_aimBot.rightDriveSpeed);

    SmartDashboard.putString("Current Color", RobotContainer.m_spinner.getCurColor());
    SmartDashboard.putNumber("Number of Colors Passed", RobotContainer.m_spinFortuneWheelThree.colorCount);
    SmartDashboard.putNumber("Red", RobotContainer.m_spinner.getRedValue());
    SmartDashboard.putNumber("Blue", RobotContainer.m_spinner.getBlueValue());
    SmartDashboard.putNumber("Green", RobotContainer.m_spinner.getGreenValue());

    SmartDashboard.putNumber("Throttle", RobotContainer.m_joystick.getThrottle());
    SmartDashboard.putNumber("Encoder Rate Left", RobotContainer.m_shooter.getEncoderRateLeft());
    SmartDashboard.putNumber("Encoder Rate Right", RobotContainer.m_shooter.getEncoderRateRight());

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //CommandScheduler.getInstance().run();
    CommandScheduler.getInstance().setDefaultCommand(RobotContainer.m_drivetrain, RobotContainer.m_drive);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
