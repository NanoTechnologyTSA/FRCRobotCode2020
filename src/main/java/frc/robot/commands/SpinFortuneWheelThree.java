/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class SpinFortuneWheelThree extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Spinner m_spinner;
  public int colorCount;
  private String curColor;
  private boolean isComplete;

  /**
   * Creates a new ExampleCommand.
   *
   * @param Spinner The subsystem used by this command.
   */
  public SpinFortuneWheelThree(Spinner spinner) {
    m_spinner = spinner;
    addRequirements(RobotContainer.m_spinner);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    colorCount = 0;
    isComplete = false;
    curColor = m_spinner.getCurColor();
    System.out.println("Spinning Fortune Wheel Three Times");;
  }

  @Override
  public void execute() {
    //Min 24
    if (colorCount < 24) {
      m_spinner.setSpeed(Constants.FORTUNE_WHEEL_SPEED);
      RobotContainer.m_drivetrain.m_robot.tankDrive(Constants.APPLY_PRESSURE_SPEED, Constants.APPLY_PRESSURE_SPEED);

      if (!(m_spinner.getCurColor()).equals(curColor) && !(m_spinner.getCurColor()).equals("white")) {
        curColor = m_spinner.getCurColor();
        colorCount++;
      }

    } else {
      isComplete = true;
      m_spinner.setSpeed(0);
      RobotContainer.m_drivetrain.m_robot.tankDrive(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_spinner.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isComplete;
  }

}
