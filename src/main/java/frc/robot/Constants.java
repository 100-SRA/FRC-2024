// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    /*
     * Two-joystick setup:
     * Joystick A => drivetrain controls
     * Joystick B => arm controls
     */
    public static final int kPortUSB_DriverJoystick_A = 0;
    public static final int kPortUSB_DriverJoystick_B = 1;

    /* Bindings */
    public static final int kButton_Trigger = 1;
    public static final int kButton_Two = 2;
    public static final int kButton_Three = 3;
  }

  public static class AutoConstants {
      /* Drive distance for leaving the start zone */
      public static final Measure<Distance> kAutoLeaveDistance = Units.Meters.of(2.0);
  }

  public static class DriveConstants {
    /* PWM Ports for drivetrain motor controllers */
    public static final int kPortPWM_Drivetrain_L = 0;
    public static final int kPortPWM_Drivetrain_R = 1;

    /* Drivetrain encoder setup */
    public static final int kEncoderPorts_Left[] = {0, 1};
    public static final int kEncoderPorts_Right[] = {2, 3};
    public static final boolean kEncoderReversed_Left = true;
    public static final boolean kEncoderReversed_Right = false;
    public static final int kEncoderCPR = 2048;
    public static final double kWheelDiameterMeters = 0.1524;
    // Encoders directly on the wheel shaft
    public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
  }

  public static class ArmConstants {
    /* CAN ids for arm lifter motor controllers */
    public static final int kCANid_ArmLift_L = 2;
    public static final int kCANid_ArmLift_R = 1;

    /* Arm speed */
    public static final double kArmAngleSpeed = 0.6;
  }

  public static class NoteThrowerConstants {
    /* CAN ids for arm throwing wheel motor controllers.
     * These numbers come directly from the wiring of the robot.
    */
    // public static final int kCANid_ThrowerWheels_Top = 4;
    // public static final int kCANid_ThrowerWheels_Bottom = 5;
    public static final int kPortPWM_ThrowerWheels_Top = 2;
    public static final int kPortPWM_ThrowerWheels_Bottom = 3;
  }

  public static class NoteIntakeConstants {
    /* CAN id for arm intake motor controllers */
    public static final int kCANid_Intake = 3;

    /* Power of the note intake motor */
    public static final double kIntakePower = 0.8;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
  }
}
