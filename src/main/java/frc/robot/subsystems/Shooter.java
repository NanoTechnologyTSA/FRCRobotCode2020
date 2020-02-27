/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
  
  private CANSparkMax m_LShooterController;
  private CANSparkMax m_RShooterController;
  private CANEncoder m_LShooterEncoder;
  private CANEncoder m_RShooterEncoder;

  private double curLeftPos;
  private double curRightPos;
    
  public Shooter() {
    m_LShooterController = new CANSparkMax(Constants.R_SHOOTER_CAN_ID, MotorType.kBrushless);
    m_RShooterController = new CANSparkMax(Constants.R_SHOOTER_CAN_ID, MotorType.kBrushless);
    m_LShooterEncoder = m_LShooterController.getEncoder();
    m_RShooterEncoder = m_RShooterController.getEncoder();
  }

  public void setSpeed(double lSpeed, double rSpeed) {
    m_LShooterController.set(lSpeed);  
    m_RShooterController.set(rSpeed);
  }
  
  public boolean setMotorToShoot() {
    double lSpeed = 0.5;
    double rSpeed = 0.5;
    while (curLeftPos != Constants.ENCODER_RATE) {
      lSpeed += 0.05;
      m_LShooterController.set(lSpeed);
    }

    while (curRightPos != Constants.ENCODER_RATE) {
      rSpeed += 0.05;
      m_RShooterController.set(rSpeed);
    }  
    
    if (curLeftPos == Constants.ENCODER_RATE && curRightPos == Constants.ENCODER_RATE) {
      return true;
    } 
    return false;
  }

  public double getEncoderRateLeft() {
    return curLeftPos;
  }

  public double getEncoderRateRight() {
    return curRightPos;
  }

  @Override
  public void periodic() {
    curLeftPos = m_LShooterEncoder.getPosition();
    curRightPos = m_RShooterEncoder.getPosition();
  }
}
