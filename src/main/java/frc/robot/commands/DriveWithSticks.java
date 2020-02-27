/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * An example command that uses an example subsystem.
 */
public class DriveWithSticks extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  public static int changeDirection;
  public static XboxController m_xboxSticks = RobotContainer.m_xboxSticks;
  public static Joystick m_joystick = RobotContainer.m_joystick;

  public DriveWithSticks() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_drivetrain.m_robot.setSafetyEnabled(false);
    changeDirection = 1;
  }


  public void triggerDrive(boolean run) {
    if (run) {
      RobotContainer.m_drivetrain.m_robot.tankDrive(changeDirection * m_xboxSticks.getTriggerAxis(Hand.kLeft), 
          changeDirection * m_xboxSticks.getTriggerAxis(Hand.kRight));

      if (m_xboxSticks.getBumper(Hand.kLeft)) {
        RobotContainer.m_drivetrain.m_robot.tankDrive(changeDirection * Constants.DT_SPIN_SPEED, changeDirection *  -Constants.DT_SPIN_SPEED);
      } else if (m_xboxSticks.getBumper(Hand.kRight)){
        RobotContainer.m_drivetrain.m_robot.tankDrive(changeDirection * -Constants.DT_SPIN_SPEED, changeDirection * Constants.DT_SPIN_SPEED);
      }

      RobotContainer.m_lifter.setSpeed(m_xboxSticks.getY(Hand.kLeft));
      RobotContainer.m_intake.setSpeed(m_xboxSticks.getY(Hand.kRight));
    }
  }

  public void sticksDrive(boolean run) {
    if (run) {
      RobotContainer.m_drivetrain.m_robot.tankDrive((m_xboxSticks.getY(Hand.kRight) + m_xboxSticks.getX(Hand.kLeft)), 
        (m_xboxSticks.getY(Hand.kRight) + (-m_xboxSticks.getX(Hand.kLeft))));

      RobotContainer.m_lifter.setSpeed(m_xboxSticks.getTriggerAxis(Hand.kLeft));
      RobotContainer.m_intake.setSpeed(m_xboxSticks.getTriggerAxis(Hand.kRight));
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //ALL XBOX BUTTONS
    //Changing Direction
    if (m_xboxSticks.getAButtonPressed()) {
      changeDirection *= -1; 
    }

    //Drive Modes 

    triggerDrive(true);
    sticksDrive(false);

    RobotContainer.m_transport.setSpeed(m_joystick.getY());

    if (m_joystick.getRawButton(3)) {
      RobotContainer.m_shooter.setSpeed(RobotContainer.m_joystick.getThrottle(), RobotContainer.m_joystick.getThrottle());
    } else {
      RobotContainer.m_shooter.setSpeed(0, 0);
    }

    if (m_joystick.getRawButton(7)) {
      RobotContainer.m_limelight.setLedMode(0);
      CommandScheduler.getInstance().schedule(RobotContainer.m_aimBot);
    } else {
      RobotContainer.m_limelight.setLedMode(1);
      CommandScheduler.getInstance().cancel(RobotContainer.m_aimBot);
    }

    if (m_joystick.getRawButtonPressed(8)) {
      CommandScheduler.getInstance().schedule(RobotContainer.m_spinFortuneWheelThree);
    }  else if (RobotContainer.m_spinFortuneWheelThree.isFinished()) {
      CommandScheduler.getInstance().cancel(RobotContainer.m_spinFortuneWheelThree);
    }

    else if (m_joystick.getRawButtonPressed(9)) {
      RobotContainer.m_fortuneWheelBot.setColor("yellow");
      CommandScheduler.getInstance().schedule(RobotContainer.m_fortuneWheelBot);
    } else if (m_joystick.getRawButtonPressed(10)) {
      RobotContainer.m_fortuneWheelBot.setColor("red");
      CommandScheduler.getInstance().schedule(RobotContainer.m_fortuneWheelBot);
    } else if (m_joystick.getRawButtonPressed(11)) {
      RobotContainer.m_fortuneWheelBot.setColor("green");
      CommandScheduler.getInstance().schedule(RobotContainer.m_fortuneWheelBot);
    } else if (m_joystick.getRawButtonPressed(12)) {
      RobotContainer.m_fortuneWheelBot.setColor("blue");
      CommandScheduler.getInstance().schedule(RobotContainer.m_fortuneWheelBot);
    } else if (RobotContainer.m_fortuneWheelBot.isFinished()) {
      CommandScheduler.getInstance().cancel(RobotContainer.m_fortuneWheelBot);
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return false;
  }
}
