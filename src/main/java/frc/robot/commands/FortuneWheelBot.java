/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class FortuneWheelBot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Spinner m_spinner;
  private String goalColor;
  private String goalColorOpposite;
  private boolean isComplete;

  /**
   * Creates a new ExampleCommand.
   *
   * @param Spinner The subsystem used by this command.
   */
  public FortuneWheelBot(Spinner spinner) {
    m_spinner = spinner;
    addRequirements(RobotContainer.m_spinner);
  }

  public void setColor(String color) {
    goalColor = color;
    if (goalColor.equals("blue")) {
      goalColorOpposite = "red";
    } else if (goalColor.equals("red")) {
      goalColorOpposite = "blue";
    } else if (goalColor.equals("yellow")) {
      goalColorOpposite = "green";
    } else if (goalColor.equals("green")) {
      goalColorOpposite = "yellow";
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isComplete = false;
  }

  //Spins the spinner 3 times and stops on the desired color
  @Override
  public void execute() {
    if (!(m_spinner.getCurColor().equals(goalColorOpposite))) {
      m_spinner.setSpeed(Constants.FORTUNE_WHEEL_SPEED);
      RobotContainer.m_drivetrain.m_robot.tankDrive(Constants.APPLY_PRESSURE_SPEED, Constants.APPLY_PRESSURE_SPEED);

    } else {
      isComplete = true;
      m_spinner.setSpeed(0);
      RobotContainer.m_drivetrain.m_robot.tankDrive(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrain.m_robot.tankDrive(0, 0);
    m_spinner.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isComplete;
  }
}
