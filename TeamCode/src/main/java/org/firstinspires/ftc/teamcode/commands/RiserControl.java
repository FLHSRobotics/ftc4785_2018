package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RiserControl {

    private DcMotor rightRise,leftRise;
    private HardwareMap hardwareMap;
    private double risePower = 0.00;

    public RiserControl(
        HardwareMap hardwareMap
    ){
        this.hardwareMap = hardwareMap;
        this.setupRiser();
    }

    private void setupRiser(){
        rightRise = hardwareMap.get(DcMotor.class,"right_rise");
        leftRise = hardwareMap.get(DcMotor.class,"left_rise");

        rightRise.setDirection(DcMotor.Direction.REVERSE);
        leftRise.setDirection(DcMotor.Direction.FORWARD);
    }

    public void riseRiser(){
        this.risePower = 1;
        this.leftRise.setPower(this.risePower);
        this.rightRise.setPower(this.risePower);
    }

    public void lowerRiser(){
        this.risePower = -1;
        this.leftRise.setPower(this.risePower);
        this.rightRise.setPower(this.risePower);
    }

    public void stopRiser(){
        this.risePower = 0;
        this.leftRise.setPower(this.risePower);
        this.rightRise.setPower(this.risePower);
    }

}
