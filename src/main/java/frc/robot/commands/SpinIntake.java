package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NoteIntakeSubsystem;

public class SpinIntake extends Command{
    private final NoteIntakeSubsystem m_intakeSubsystem;
    
    public SpinIntake(NoteIntakeSubsystem subsystem){
        m_intakeSubsystem = subsystem;
        addRequirements(m_intakeSubsystem);
    }

    /*called the activate intake for the arm subsystem, once called it runs until the button is pressed to stop */
    @Override 
    public void execute(){
        m_intakeSubsystem.activateIntake();
    }

    /* ensuring that the command caller knows that the function has been ran. another function that ensures the motors completely stop spinning once the button is pressed again.*/
    @Override 
    public boolean isFinished(){
        return false;
    } 

    /* following the structure of the command properties overriding the interrupted func. */
    @Override
    public void end(boolean interrupted){
        m_intakeSubsystem.deactivateIntake();
    }
} 