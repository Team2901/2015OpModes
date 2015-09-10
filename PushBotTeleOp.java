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
    double minL, maxL, minR, maxR;
    double targetValue;
    double lPosition, rPosition;
    double lOffset, rOffset;
    public void loop()
    {
        // collects joystick values from the left and right joystick to set motor values
        float leftThrottle = gamepad1.left_stick_y;
        float rightThrottle = gamepad1.right_stick_y;
        leftPower = leftThrottle;
        rightPower = rightThrottle;
        rightMotor.setPower(rightPower);
        leftMotor.setPower(leftPower);
        telemetry.addData("1", leftThrottle);
        telemetry.addData("2", rightThrottle);
        if (gamepad1.a)
        {
            armMotor.setPower(-.5);
        }
        if (gamepad1.b)
        {
            armMotor.setPower(.5);
        }
        if (!gamepad1.a && !gamepad1.b)
        {
            armMotor.setPower(0);
        }

        if (gamepad1.left_trigger > 0 && targetValue <= 1)
        {
            targetValue += .005 * (gamepad1.left_trigger + .1);
        }
        if (gamepad1.right_trigger > 0 && targetValue >= 0)
        {
            targetValue += -.005 * (gamepad1.right_trigger + .1);
        }
        telemetry.addData("1", targetValue);
        lPosition = targetValue + lOffset;
        rPosition = targetValue  + rOffset;
        lServo.setPosition(lPosition);
        rServo.setPosition(rPosition);

    }
}
