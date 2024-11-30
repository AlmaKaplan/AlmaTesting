
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

  private double counter;


  private Transfer() {
    motor = new TalonFX(PortMap.TransferPorts.TRANSFER_MOTOR);

    firstSensor = new DigitalInput(PortMap.TransferPorts.TRANSFER_SENSOR1);
    secondSsensor = new DigitalInput(PortMap.TransferPorts.TRANSFER_SENSOR2);
    thirdSSensor = new DigitalInput(PortMap.TransferPorts.TRANSFER_SENSOR3);

    currentDrawMotor = motor.getSupplyCurrent();
    velocityMotor =  motor.getVelocity();
    motorTempMotor = motor.getDeviceTemp();
    appliedVoltageMotor = motor.getMotorVoltage();
    positionMotor = motor.getPosition();

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

  public double BallCounter() {
    counter = 0;
    if (firstSensor.get()) {
      counter++;
    }
    if (secondSsensor.get()) {
      counter++;
    }
    if (thirdSSensor.get()) {
      counter++;
    }
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
    board.addNum("Amount Of game piece", BallCounter());
  }
}
