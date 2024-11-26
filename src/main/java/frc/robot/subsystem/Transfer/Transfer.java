
package frc.robot.subsystem.Transfer;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ma5951.utils.DashBoard.MAShuffleboard;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Transfer extends SubsystemBase {
  public static Transfer transfer;

  private MAShuffleboard board;

  private TalonFX motor;
  private TalonFXConfiguration motorCunfig;
  private DigitalInput firstSensor;
  private DigitalInput secondSsensor;
  private DigitalInput thirdSSensor;


  private StatusSignal<Current> currentDrawMotor;
  private StatusSignal<AngularVelocity> velocityMotor;
  private StatusSignal<Temperature> motorTempMotor;
  private StatusSignal<Voltage> appliedVoltageMotor;
  private StatusSignal<Angle> positionMotor;

  private double counter1;
  private double counter2;
  private double counter3;
  private double counter;


  private Transfer() {
    motor = new TalonFX(PortMap.Transfer.TRANSFER_MOTOR);

    currentDrawMotor = motor.getSupplyCurrent();
    velocityMotor =  motor.getVelocity();
    motorTempMotor = motor.getDeviceTemp();
    appliedVoltageMotor = motor.getMotorVoltage();
    positionMotor = motor.getPosition();
    counter1 = 0;
    counter2 = 0;
    counter3 = 0;
    counter = 0;
    board = new MAShuffleboard("Transfer");

    Cunfig();

  }

  private void Cunfig() {
    motorCunfig.Feedback.RotorToSensorRatio = TransferConstance.TRANSFER_GEAR;
    
    motorCunfig.CurrentLimits.StatorCurrentLimitEnable = TransferConstance.ENABLE_CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLimit = TransferConstance.CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerLimit = TransferConstance.CONTINOUS_POWER_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerTime = TransferConstance.CONTINOUS_POWER_DURATION;

    motorCunfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motorCunfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorCunfig.Voltage.PeakForwardVoltage = 12;
    motorCunfig.Voltage.PeakReverseVoltage = -12;
    

    motor.getConfigurator().apply(motorCunfig);
  }

  public void setVoltage(double volt) {
    motor.setVoltage(volt);
  }

  public void getFirstGamepiece() {
    if(firstSensor.get()){
      if(counter1 == 0){
        counter1++;
      } 
    } else {
      counter1 = 0;
    }
  }

  public void getSecondGamepiece() {
    if(secondSsensor.get()){
      if(counter2 == 0){
        counter2++;
      } 
    } else {
      counter2 = 0;
    }
  }

  public void getThirdGamepiece() {
    if(thirdSSensor.get()){
      if(counter3 == 0){
        counter3++;
      } 
    } else {
      counter3 = 0;
    }
  }

  public double getAmountGamePiece(){
    counter = counter1 + counter2 + counter3;
    return counter;
  }


  public double getCurrentDraw() {
    currentDrawMotor.refresh();
    return currentDrawMotor.getValueAsDouble();
  }


  public double getVelocity() {
    velocityMotor.refresh();
    return velocityMotor.getValueAsDouble();
  }


  public double getMotorTemp() {
    motorTempMotor.refresh();
    return motorTempMotor.getValueAsDouble();
  }


  public double getAppliedVoltage() {
    appliedVoltageMotor.refresh();
    return appliedVoltageMotor.getValueAsDouble();
  }


  public double getPosition() {
    positionMotor.refresh();
    return positionMotor.getValueAsDouble();
  }

  public static Transfer getInstance() {
    if (transfer == null) {
      transfer = new Transfer();
    }
    return transfer;

  }
  @Override
  public void periodic() {
    board.addNum("Amount Of game piece", getAmountGamePiece());
  }
}
