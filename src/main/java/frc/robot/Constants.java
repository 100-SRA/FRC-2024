// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
  }

  public static class DriveConstants {
    /* PWM Ports for drivetrain motor controllers */
    public static final int kPortPWM_Drivetrain_L = 0;
    public static final int kPortPWM_Drivetrain_R = 1;
  }

  public static class ArmConstants {
    /* CAN ids for arm lifter motor controllers */
    public static final int kCANid_ArmLift_L = 2;
    public static final int kCANid_ArmLift_R = 1;

    /* CAN id for arm intake motor controllers */
    public static final int kCANid_Intake = 3;

    /* CAN id for arm throwing wheel motor controllers */
    public static final int kCANid_ThrowerWheels_Top = 4;
    public static final int kCANid_ThrowerWheels_Bottom = 5;
  }
}
