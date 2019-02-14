package org.firstinspires.ftc.teamcode.sandbox.stages;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.VuforiaNavigation;


@Autonomous(name="Claim Auto",group="debug opMode")
public class ClaimAuto extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private VuforiaNavigation vuforiaNavigation;

    @Override
    public void runOpMode() throws InterruptedException{
        mecanumDrive = new MecanumDrive(hardwareMap);
        vuforiaNavigation = new VuforiaNavigation(hardwareMap,telemetry);

        waitForStart();

        if(!vuforiaNavigation.isTargetVisible()){
            mecanumDrive.moveParaLeft();
        }

    }


}