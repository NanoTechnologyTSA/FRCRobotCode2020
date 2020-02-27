/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


//Network Tables API Link (For All Values): http://docs.limelightvision.io/en/latest/networktables_api.html
public class Limelight extends SubsystemBase {
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private boolean tv = table.getEntry("tv").getBoolean(false);
  private double tx = table.getEntry("tx").getDouble(0.0);
  private double ty = table.getEntry("ty").getDouble(0.0);
  private double ta = table.getEntry("ta").getDouble(0.0);
  private double ts = table.getEntry("ts").getDouble(0.0);
  private double tl = table.getEntry("tl").getDouble(0.0);

  public Limelight() {}

  public boolean isTarget() {
    return tv; 
  }

  public double getXPos() {
    return tx;
  }

  public double getYPos() {
    return ty;
  }

  public double getImageArea() {
    return ta;
  }

  public double getSkew() {
    return ts;
  }

  public double getLatency() {
    return tl;
  }

  public void setLedMode(int lightMode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(lightMode);
  }

  public void setCameraMode(int cameraMode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("cameraMode").setNumber(cameraMode);
  }

  public void setPipeline(int num) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(num);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    tv = table.getEntry("tv").getBoolean(false);
    tx = table.getEntry("tx").getDouble(0.0);
    ty = table.getEntry("ty").getDouble(0.0);
    ta = table.getEntry("ta").getDouble(0.0);
    ts = table.getEntry("ts").getDouble(0.0);
    tl = table.getEntry("tl").getDouble(0.0);
  }
}
