package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NoteThrowerSubsystem;

/*
 * Command to start the thrower wheels spinning to launch a note.
 */
public class ThrowNote extends Command {
    private final NoteThrowerSubsystem m_throwerSubsystem;
    private final DoubleSupplier m_speedControl;

    public ThrowNote(NoteThrowerSubsystem subsystem, DoubleSupplier speedControl) {
        m_throwerSubsystem = subsystem;
        m_speedControl = speedControl;
        addRequirements(m_throwerSubsystem);
    }

    @Override
    public void execute() {
        // set wheel speed based on joystick dial
        // need to convert from joystick dial's range of 1.0 at bottom, -1.0 at top to
        // desired range of 0.0 (0% speed) at bottom to 1.0 (100% speed) at top
        // steps to do so:
        // 1. invert (*= -1) -> -1.0 at bottom, +1.0 at top
        // 2. add 1 -> 0.0 at bottom, +2.0 at top
        // 3. divide by 2 -> 0.0 at bottom, +1.0 at top
        double rawSpeedValue = m_speedControl.getAsDouble();
        double speedAsPercentage = (-1 * rawSpeedValue + 1) / 2.0;
        m_throwerSubsystem.setSpeed(speedAsPercentage);
        m_throwerSubsystem.spinThrowers();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_throwerSubsystem.stopThrowers();
    }
}
