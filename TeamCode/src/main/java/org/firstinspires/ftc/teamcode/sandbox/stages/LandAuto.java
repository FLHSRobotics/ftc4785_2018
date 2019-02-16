package org.firstinspires.ftc.teamcode.sandbox.stages;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.RiserControl;

@Autonomous(name="Landing Auto",group="debug OpMode")
public class LandAuto extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private RiserControl riserControl;

    @Override
    public void runOpMode() throws InterruptedException{

        mecanumDrive = new MecanumDrive(hardwareMap);
        riserControl = new RiserControl(hardwareMap);
        waitForStart();

        riserControl.riseRiser();
        Thread.sleep(6500);
        riserControl.stopRiser();

        mecanumDrive.moveParaRight(600);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        mecanumDrive.moveFwd(1200);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        mecanumDrive.moveParaRight(850);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();


    }
}
