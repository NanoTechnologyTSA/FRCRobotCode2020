/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

/**
 * An example command that uses an example subsystem.
 */
public class AimBot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private Limelight m_limelight;

  //-0.1, -0.1, 0.5
  private static final double KpAim = -0.6;
  private static final double KpDistance = -0.1;
  private static final double min_aim_command = 0.7;

  public double distanceAdjust;
  public double leftDriveSpeed;
  public double rightDriveSpeed;

  private static boolean isComplete;

  /**
   * Creates a new ExampleCommand.
   *
   * @param Limelight The subsystem used by this command.
   */
  public AimBot (Limelight limelight) {
    m_limelight = limelight;
    addRequirements(RobotContainer.m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isComplete = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xPos = m_limelight.getXPos();

    double headingError = -m_limelight.getXPos();
    double distanceError = -m_limelight.getYPos();
    double steeringAdjust = 0.0;

    if (xPos > 1.0) {
        steeringAdjust = KpAim*headingError - min_aim_command;

    } else if (xPos < 1.0) {
        steeringAdjust = KpAim*headingError + min_aim_command;

    }

    distanceAdjust = KpDistance * distanceError;
    leftDriveSpeed =+ steeringAdjust + distanceAdjust;
    rightDriveSpeed =- steeringAdjust + distanceAdjust;

    RobotContainer.m_drivetrain.m_robot.tankDrive(leftDriveSpeed, rightDriveSpeed);

    if (leftDriveSpeed < 0.2 && rightDriveSpeed < 0.2) {
      if (RobotContainer.m_shooter.setMotorToShoot()) {
        RobotContainer.m_transport.setSpeed(Constants.TRANSPORT_SPEED);
      } 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isComplete;
  }
}
