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
        Thread.sleep(10000);
        riserControl.stopRiser();

        mecanumDrive.moveParaRight();
        Thread.sleep(500);
        mecanumDrive.stopMove();

        mecanumDrive.moveFwd();
        Thread.sleep(500);
        mecanumDrive.stopMove();

        mecanumDrive.moveParaRight();
        Thread.sleep(2000);
        mecanumDrive.stopMove();

    }
}
