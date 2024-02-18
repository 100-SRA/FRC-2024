// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DefaultArmLift;
// import frc.robot.commands.Autos;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.LimitArmSpeed;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ArmSubsystem m_armLift = new ArmSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandJoystick m_driverJoystickA =
      new CommandJoystick(OperatorConstants.kPortUSB_DriverJoystick_A);
    private final CommandJoystick m_driverJoystickB =
      new CommandJoystick(OperatorConstants.kPortUSB_DriverJoystick_B);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Configure default commands
    // Set default drive command to single-stick arcadge drive
    m_robotDrive.setDefaultCommand(
        Commands.parallel(
          /**
           * Single-stick arcade command, with forward/backward 
           * and turning controlled by different axes of the first joystick.
           */
          new DefaultDrive(
              m_robotDrive,
              () -> -m_driverJoystickA.getY(),
              () -> -m_driverJoystickA.getX()),
          /**
           * Control the angle of the arm with the second joystick, limiting its speed to 0.5.
           */
          new DefaultArmLift(
            m_armLift,
            () -> -m_driverJoystickB.getY()),
          new LimitArmSpeed(m_armLift)
        )
    );
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverJoystickA.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // TODO(malik): Implement an autonomous program
  /*
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    ;
  }
  */
}
