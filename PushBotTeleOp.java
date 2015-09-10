package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by chavezm17060 on 9/8/2015.
 */
public class PushBotTeleOp extends OpMode{
    public void init()
    {
        // initializez robot by initilizing motors and setting the left motor to reverse
        telemetry.addData("1", "Robot Initializing");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        armMotor = hardwareMap.dcMotor.get("armMotor");
        lServo = hardwareMap.servo.get("lServo");
        rServo = hardwareMap.servo.get("rServo");
        lServo.scaleRange(minL, maxL);
        rServo.scaleRange(minR, maxR);

    }
    // introducing variables
    float leftPower, rightPower;
    DcMotor rightMotor, leftMotor, armMotor;
    Servo lServo, rServo;
    double minL = 0;
    double maxL = 1;
    double minR = 0;
    double maxR = 1;
    double targetValue;
    double lPosition, rPosition;
    double lOffset, rOffset;
    double armSpeed = 1;
    double moveSpeed = 1;
    int loopnumber;
    int marker;

    public void loop()
    {
        // collects joystick values from the left and right joystick to set motor values
        float leftThrottle = gamepad1.left_stick_y;
        float rightThrottle = gamepad1.right_stick_y;
        leftPower = leftThrottle ;
        rightPower = rightThrottle ;
        if (Math.abs(rightPower) > .05 || Math.abs(leftPower) > .05)
        {
            if ( (Math.abs(rightPower) > .5 ) && rightPower + leftPower < .2) //"if the motors are moving at a substantial speed in opposite direction"
            {
                // slows down the motors while turning
                rightMotor.setPower(rightPower* moveSpeed*.5);
                leftMotor.setPower(leftPower* moveSpeed*.5);
                telemetry.addData("3", "Turn Speed Control activated");
            }else {
                // if the motors are not turning, motors will turn at their intended speed
                rightMotor.setPower(rightPower * moveSpeed);
                leftMotor.setPower(leftPower * moveSpeed);
                telemetry.addData("3", "Turn Speed Control NOT activated");

            }
        }

        telemetry.addData("1", leftThrottle);
        telemetry.addData("2", rightThrottle);
        if (gamepad1.right_bumper && (loopnumber > 15 ) )
        { // marker tells the program what mode the robot is currently in, easy or fast
            if (marker == 0) {
                armSpeed = .25;
                moveSpeed = .5;
                marker = 1;
            }
            if (marker == 1)
            {
                armSpeed = 1;
                moveSpeed = 1;
                marker = 0;
            }
            loopnumber = 0;
        }
        if (gamepad1.a)
        {
            armMotor.setPower(-.5 * armSpeed);
        }
        if (gamepad1.b)
        {
            armMotor.setPower(.5 * armSpeed);
        }
        if (!gamepad1.a && !gamepad1.b)
        {
            armMotor.setPower(0);
        }

        if (gamepad1.left_trigger > 0 && targetValue <= .9)
        {
            targetValue += .005 * (gamepad1.left_trigger + .1);
        }
        if (gamepad1.right_trigger > 0 && targetValue >= .1)
        {
            targetValue += -.005 * (gamepad1.right_trigger + .1);
        }
        telemetry.addData("1", moveSpeed);
        telemetry.addData("2", armSpeed);
        lPosition = -targetValue + 1;
        rPosition = targetValue  + rOffset;
        lServo.setPosition(lPosition);
        rServo.setPosition(rPosition);
        loopnumber++;

    }
}
