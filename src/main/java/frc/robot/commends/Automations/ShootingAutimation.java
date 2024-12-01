
package frc.robot.commends.Automations;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystem.DriveTank.DriveTank;
import frc.robot.subsystem.Shooter.Shooter;
import frc.robot.subsystem.Shooter.ShooterConstance;
import frc.robot.subsystem.Transfer.Transfer;
import frc.robot.subsystem.Transfer.TransferConstance;

public class ShootingAutimation extends SequentialCommandGroup {

  public ShootingAutimation() {
    if (DriveTank.getInstance().SpeedCalculationLeft() && DriveTank.getInstance().SpeedCalculationRight()){
      if (Transfer.getInstance().BallCounter() <3){
        addCommands(new IntakeAutomation());
      }
      for (int i = 0; i <3; i++){
        addCommands( 
          new InstantCommand(() ->  Shooter.getInstance().setVoltage(ShooterConstance.SHOOTING_VOLTAGE)),
          new WaitUntilCommand(() -> Shooter.getInstance().shooterSpeed()),
          new InstantCommand(() -> Transfer.getInstance().setVoltage(TransferConstance.TRANSPORT_VOLTAGE_FOR_SHOOTING))
          
        );
      }

      addCommands(
        new InstantCommand(() -> Shooter.getInstance().setVoltage(0)),
        new InstantCommand(() -> Transfer.getInstance().setVoltage(0)));
      
      
    }
  }
}
