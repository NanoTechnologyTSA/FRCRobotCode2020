/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  
  private Spark m_LBDTController;
  private Spark m_LFDTController;
  private Spark m_RBDTController;
  private Spark m_RFDTController;

  private SpeedControllerGroup m_leftSCG;
  private SpeedControllerGroup m_rightSCG;

  public DifferentialDrive m_robot;
  
  public Drivetrain() {
  
    m_LBDTController = new Spark(Constants.LB_DT_PORT);
    m_LFDTController = new Spark(Constants.LF_DT_PORT);
    m_RBDTController = new Spark(Constants.RB_DT_PORT);
    m_RFDTController = new Spark(Constants.RF_DT_PORT);
    
    m_leftSCG = new SpeedControllerGroup(m_LBDTController, m_LFDTController);
    m_rightSCG = new SpeedControllerGroup(m_RBDTController, m_RFDTController);

    m_robot = new DifferentialDrive(m_leftSCG, m_rightSCG);
  
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
