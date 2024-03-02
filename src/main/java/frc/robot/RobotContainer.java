// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DefaultArmLift;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.ReverseDriveDirection;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.SwitchIntakeDirection;
import frc.robot.commands.ThrowNote;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NoteIntakeSubsystem;
import frc.robot.subsystems.NoteThrowerSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems and commands are defined here...
        private final DriveSubsystem m_drivetrainSystem = new DriveSubsystem();
        private final ArmSubsystem m_armSystem = new ArmSubsystem();
        private final NoteIntakeSubsystem m_intakeSystem = new NoteIntakeSubsystem();
        private final NoteThrowerSubsystem m_throwerSystem = new NoteThrowerSubsystem();

        // Replace with CommandPS4Controller or CommandJoystick if needed
        private final CommandJoystick m_driverJoystickA = new CommandJoystick(
                        OperatorConstants.kPortUSB_DriverJoystick_A);
        private final CommandJoystick m_driverJoystickB = new CommandJoystick(
                        OperatorConstants.kPortUSB_DriverJoystick_B);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the trigger bindings
                configureBindings();

                // Configure default commands
                // Set default drive command to single-stick arcadge drive
                m_drivetrainSystem.setDefaultCommand(
                                /**
                                 * Single-stick arcade command, with forward/backward
                                 * and turning controlled by different axes of the first joystick.
                                 * Speed control via the Z axis (dial at the base of the joystick).
                                 */
                                new DefaultDrive(
                                                m_drivetrainSystem,
                                                () -> -m_driverJoystickA.getY(),
                                                () -> -m_driverJoystickA.getX(),
                                                () -> m_driverJoystickA.getZ())

                );

                m_armSystem.setDefaultCommand(
                                /**
                                 * Control the angle of the arm with the second joystick.
                                 */
                                new DefaultArmLift(
                                                m_armSystem,
                                                () -> -m_driverJoystickB.getY()));
        }

        /**
         * Use this method to define your trigger->command mappings. Triggers can be
         * created via the
         * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
         * an arbitrary
         * predicate, or via the named factories in {@link
         * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
         * {@link
         * CommandXboxController
         * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
         * PS4} controllers or
         * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
         * joysticks}.
         */
        private void configureBindings() {
                /* Command to reverse drive direction by toggling a button on joystick A */
                new JoystickButton(m_driverJoystickA.getHID(), OperatorConstants.kButton_Two)
                                .onTrue(new ReverseDriveDirection(m_drivetrainSystem));

                /*
                 * Command to spin the intake wheels while holding a button down on joystick B
                 */
                new JoystickButton(m_driverJoystickB.getHID(), OperatorConstants.kButton_Two)
                                .whileTrue(new SpinIntake(m_intakeSystem));
                
                /*
                 * Command to flip the intake wheel direction each time you press a certain button on joystick B 
                 */
                new JoystickButton(m_driverJoystickB.getHID(), OperatorConstants.kButton_Three)
                                .onTrue(new SwitchIntakeDirection(m_intakeSystem));

                /*
                 * Command to spin the throwing wheels while holding down the trigger on
                 * joystick B, while modulating the speed based on the joystick's Z axis (dial)
                 */
                new JoystickButton(m_driverJoystickB.getHID(), OperatorConstants.kButton_Trigger)
                                .whileTrue(new ThrowNote(m_throwerSystem, () -> m_driverJoystickB.getZ()));
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        // TODO(malik): Implement an autonomous program
        /*
         * public Command getAutonomousCommand() {
         * // An example command will be run in autonomous
         * // return Autos.exampleAuto(m_exampleSubsystem);
         * ;
         * }
         */
}
