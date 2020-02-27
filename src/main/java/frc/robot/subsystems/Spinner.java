/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants;
import com.revrobotics.ColorSensorV3;


public class Spinner extends SubsystemBase {
  
  //Color sensor and infrared light reading
  private ColorSensorV3 m_colorSensor = new ColorSensorV3(Constants.i2cPort);
  private Color detectedColor = m_colorSensor.getColor();
  private double IR = m_colorSensor.getIR();
  
  private Spark m_SpinnerController;

  public Spinner() {
    m_SpinnerController = new Spark(Constants.SPINNER_PORT);

  }
  
  public void setSpeed(double speed) {
    m_SpinnerController.set(speed);
  }

  public double getRedValue() {
    return detectedColor.red;
  }

  public double getGreenValue() {
    return detectedColor.green;
  }

  public double getBlueValue() {
    return detectedColor.blue;
  }

  public double getIR() {
    return IR;
  }

  public String getCurColor() {
    if (getBlueValue() > 0.31 && getGreenValue() > 0.42 && getRedValue() > 0.13 && getBlueValue() < 0.40 && getGreenValue() < 0.51 && getRedValue() < 0.21) {
      return "blue";
    } /* Competition Green -> else if (getBlueValue() > 0.23 && getGreenValue() > 0.51 && getRedValue() > 0.13 && getBlueValue() < 0.48 && getGreenValue() < 0.64 && getRedValue() < 0.27) {
      return "green";
    } */ /*Test Green -> */else if (getBlueValue() > 0.19 && getGreenValue() > 0.51 && getRedValue() > 0.17 && getBlueValue() < 0.27 && getGreenValue() < 0.59 && getRedValue() < 0.25) {
      return "green";
    } else if (getBlueValue() > 0.12 && getGreenValue() > 0.32 && getRedValue() > 0.43 && getBlueValue() < 0.20 && getGreenValue() < 0.40 && getRedValue() < 0.51) {
      return "red";
    } else if (getBlueValue() > 0.08 && getGreenValue() > 0.50 && getRedValue() > 0.29 && getBlueValue() < 0.16 && getGreenValue() < 0.58 && getRedValue() < 0.37) /*30 59 10 34 49 19*/ {
      return "yellow";
    } else {
      return "white";
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    detectedColor = m_colorSensor.getColor();
    IR = m_colorSensor.getIR();
  }
}
