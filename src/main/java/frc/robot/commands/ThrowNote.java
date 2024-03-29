package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.NoteIntakeSubsystem;
import frc.robot.subsystems.NoteThrowerSubsystem;

/*
 * Composite command assuming there is a note in possession:
 * 1. spin up the thrower wheels
 * 2. wait for a few seconds
 * 3. spin the intake wheels for a short time (push note into throwers)
 * 4. stop both sets of wheels
 */
public class ThrowNote extends Command {
    private ParallelDeadlineGroup m_throwNote;

    static private final double kThrowerSpinupTime = 3; /* seconds */
    static private final double kIntakeNotePushTime = 1; /* seconds */

    static public Command buildCommand(NoteThrowerSubsystem thrower, NoteIntakeSubsystem intake) {
        /* Create a sequential command chain that first waits a while,
         * then spins the intake wheels for a short time to push the note up
         * into the throwers
         */
        SequentialCommandGroup pushNoteIntoThrowers = new SequentialCommandGroup(
                new WaitCommand(kThrowerSpinupTime),
                new SpinIntake(intake).withTimeout(kIntakeNotePushTime));

        /* Create a group command that runs two commands in parallel:
         * A. command to start the thrower wheels spinning
         * B. command to push-note-into-throwers-after-delay
         *
         * This group command should stop the thrower-wheel-spinning command
         * as soon as the push-note-into-throwers-after-delay command ends
         */
        return new ParallelDeadlineGroup(
                pushNoteIntoThrowers, new SpinThrowerWheels(thrower));
    }

    @Override
    public void initialize() {
        /* Schedule the composite command we created in the constructor to be
         * ran by the Command Scheduler
         */
        m_throwNote.schedule();
    }

    @Override
    public boolean isFinished() {
        return m_throwNote.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        m_throwNote.end(interrupted);
    }
}
