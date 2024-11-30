
package frc.robot.subsystem.DriveTank;

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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class DriveTank extends SubsystemBase {
  public static DriveTank driveTank;

  private MAShuffleboard board;

  private TalonFX rightFrontMotor;
  private TalonFX rightBackMotor;
  private TalonFX leftFrontMotor;
  private TalonFX leftBackMotor;
  private TalonFXConfiguration Cunfig;

  private StatusSignal<Current> currentDrawMotorLF;
  private StatusSignal<AngularVelocity> velocityMotorLF;
  private StatusSignal<Temperature> motorTempMotorLF;
  private StatusSignal<Voltage> appliedVoltageMotorLF;
  private StatusSignal<Angle> positionMotorLF;

  private StatusSignal<Current> currentDrawMotorRF;
  private StatusSignal<AngularVelocity> velocityMotorRF;
  private StatusSignal<Temperature> motorTempMotorRF;
  private StatusSignal<Voltage> appliedVoltageMotorRF;
  private StatusSignal<Angle> positionMotorRF;

  private StatusSignal<Current> currentDrawMotorLB;
  private StatusSignal<AngularVelocity> velocityMotorLB;
  private StatusSignal<Temperature> motorTempMotorLB;
  private StatusSignal<Voltage> appliedVoltageMotorLB;
  private StatusSignal<Angle> positionMotorLB;

  private StatusSignal<Current> currentDrawMotorRB;
  private StatusSignal<AngularVelocity> velocityMotorRB;
  private StatusSignal<Temperature> motorTempMotorRB;
  private StatusSignal<Voltage> appliedVoltageMotorRB;
  private StatusSignal<Angle> positionMotorRB;

  private DriveTank() {
    rightFrontMotor = new TalonFX(PortMap.DriveTankPorts.DRIVE_TANK_RIGHT_FRONT_MOTOR);
    rightBackMotor = new TalonFX(PortMap.DriveTankPorts.DRIVE_TANK_RIGHT_BACK_MOTOR);
    leftFrontMotor = new TalonFX(PortMap.DriveTankPorts.DRIVE_TANK_LEFT_FRONT_MOTOR);
    leftBackMotor = new TalonFX(PortMap.DriveTankPorts.DRIVE_TANK_LEFT_BACK_MOTOR);

    board = new MAShuffleboard("tank drive");

    currentDrawMotorLF = rightFrontMotor.getSupplyCurrent();
    velocityMotorLF =  rightFrontMotor.getVelocity();
    motorTempMotorLF = rightFrontMotor.getDeviceTemp();
    appliedVoltageMotorLF = rightFrontMotor.getMotorVoltage();
    positionMotorLF = rightFrontMotor.getPosition();

    currentDrawMotorRF = rightFrontMotor.getSupplyCurrent();
    velocityMotorRF =  rightFrontMotor.getVelocity();
    motorTempMotorRF = rightFrontMotor.getDeviceTemp();
    appliedVoltageMotorRF = rightFrontMotor.getMotorVoltage();
    positionMotorRF = rightFrontMotor.getPosition();

    currentDrawMotorLB = rightFrontMotor.getSupplyCurrent();
    velocityMotorLB =  rightFrontMotor.getVelocity();
    motorTempMotorLB = rightFrontMotor.getDeviceTemp();
    appliedVoltageMotorLB = rightFrontMotor.getMotorVoltage();
    positionMotorLB = rightFrontMotor.getPosition();

    currentDrawMotorRB = rightFrontMotor.getSupplyCurrent();
    velocityMotorRB =  rightFrontMotor.getVelocity();
    motorTempMotorRB = rightFrontMotor.getDeviceTemp();
    appliedVoltageMotorRB = rightFrontMotor.getMotorVoltage();
    positionMotorRB = rightFrontMotor.getPosition();

    Cunfig = new TalonFXConfiguration();

    LeftCunfig();
    rightCunfig();
  }

  private void LeftCunfig() {
    Cunfig.Feedback.RotorToSensorRatio = DriveTankConstance.DRIVE_TANK_GEAR;

    Cunfig.CurrentLimits.SupplyCurrentLimitEnable = DriveTankConstance.ENABLE_CURRENT_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLimit = DriveTankConstance.CURRENT_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLowerLimit = DriveTankConstance.CONTINOUS_POWER_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLowerTime = DriveTankConstance.CONTINOUS_POWER_DURATION;

    Cunfig.Voltage.PeakForwardVoltage = 12;
    Cunfig.Voltage.PeakReverseVoltage = -12;

    Cunfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    Cunfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    rightFrontMotor.getConfigurator().apply(Cunfig);
    rightBackMotor.getConfigurator().apply(Cunfig);
    
  }

  private void rightCunfig() {
    Cunfig.Feedback.RotorToSensorRatio = DriveTankConstance.DRIVE_TANK_GEAR;

    Cunfig.CurrentLimits.SupplyCurrentLimitEnable = DriveTankConstance.ENABLE_CURRENT_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLimit = DriveTankConstance.CURRENT_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLowerLimit = DriveTankConstance.CONTINOUS_POWER_LIMIT;
    Cunfig.CurrentLimits.SupplyCurrentLowerTime = DriveTankConstance.CONTINOUS_POWER_DURATION;

    Cunfig.Voltage.PeakForwardVoltage = 12;
    Cunfig.Voltage.PeakReverseVoltage = -12;

    Cunfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    Cunfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    leftBackMotor.getConfigurator().apply(Cunfig);
    leftFrontMotor.getConfigurator().apply(Cunfig);
    
  }

  public void setVoltageRight(double volt) {
    rightFrontMotor.setVoltage(volt);
    rightBackMotor.setVoltage(volt);
  }

  public void setVoltageLeft(double volt) {
    leftFrontMotor.setVoltage(volt);
    leftBackMotor.setVoltage(volt);
  }

  public double getLeftFrontMotorCurrentDraw() {
    currentDrawMotorLF.refresh();
    return currentDrawMotorLF.getValueAsDouble();
  }


  public double getLeftFrontMotorVelocity() {
    velocityMotorLF.refresh();
    return velocityMotorLF.getValueAsDouble();
  }


  public double getLefFronttMotorTemp() {
    motorTempMotorLF.refresh();
    return motorTempMotorLF.getValueAsDouble();
  }


  public double getLefFronttMotorAppliedVoltage() {
    appliedVoltageMotorLF.refresh();
    return appliedVoltageMotorLF.getValueAsDouble();
  }


  public double getLeftFrontMotorPosition() {
    positionMotorLF.refresh();
    return positionMotorLF.getValueAsDouble();
  }

  public double getRightFrontMotorCurrentDraw() {
    currentDrawMotorRF.refresh();
    return currentDrawMotorRF.getValueAsDouble();
  }


  public double getRightFrontMotorVelocity() {
    velocityMotorRF.refresh();
    return velocityMotorRF.getValueAsDouble();
  }


  public double getRightFrontMotorTemp() {
    motorTempMotorRF.refresh();
    return motorTempMotorRF.getValueAsDouble();
  }


  public double getRightFrontMotorAppliedVoltage() {
    appliedVoltageMotorRF.refresh();
    return appliedVoltageMotorRF.getValueAsDouble();
  }


  public double getRightFrontMotorPosition() {
    positionMotorRF.refresh();
    return positionMotorRF.getValueAsDouble();
  }

  public double getLeftBackMotorCurrentDraw() {
    currentDrawMotorLB.refresh();
    return currentDrawMotorLB.getValueAsDouble();
  }


  public double getLefBacktMotorVelocity() {
    velocityMotorLB.refresh();
    return velocityMotorLB.getValueAsDouble();
  }


  public double getLeftBackMotorTemp() {
    motorTempMotorLB.refresh();
    return motorTempMotorLB.getValueAsDouble();
  }


  public double getLefBacktMotorAppliedVoltage() {
    appliedVoltageMotorLB.refresh();
    return appliedVoltageMotorLB.getValueAsDouble();
  }


  public double getLeftBackMotorPosition() {
    positionMotorLB.refresh();
    return positionMotorLB.getValueAsDouble();
  }

  public double getRightBackMotorCurrentDraw() {
    currentDrawMotorRB.refresh();
    return currentDrawMotorRB.getValueAsDouble();
  }


  public double getRightBackMotorVelocity() {
    velocityMotorRB.refresh();
    return velocityMotorRB.getValueAsDouble();
  }


  public double getRightBackMotorTemp() {
    motorTempMotorRB.refresh();
    return motorTempMotorRB.getValueAsDouble();
  }


  public double getRightBackMotorAppliedVoltage() {
    appliedVoltageMotorRB.refresh();
    return appliedVoltageMotorRB.getValueAsDouble();
  }


  public double getRightBackMotorPosition() {
    positionMotorRB.refresh();
    return positionMotorRB.getValueAsDouble();
  }

  public double SpeedCalculationRight(){
    double x = getRightBackMotorVelocity() * DriveTankConstance.RIVE_TANK_WHEEL_SIZE_IN_M;
    return x;
  }

  public double SpeedCalculationLeft(){
    double x = getLefBacktMotorVelocity() * DriveTankConstance.RIVE_TANK_WHEEL_SIZE_IN_M;
    return x;
  }


  public static DriveTank getInstance() {
    if (driveTank == null) {
      driveTank = new DriveTank();
    }
    return driveTank;
  }


  @Override
  public void periodic() {
    board.addNum("right speed", getRightBackMotorVelocity());
    board.addNum("left speed", getLefBacktMotorVelocity());

  }
}
