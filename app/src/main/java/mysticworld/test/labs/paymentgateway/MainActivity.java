package mysticworld.test.labs.paymentgateway;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    EditText amount;
    Button pay;
    int paymentamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.amount);
        pay = findViewById(R.id.payBtn);
        Checkout.preload(getApplicationContext());
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPayment();
            }


        });

    }

    private void startPayment() {
        paymentamount = Integer.parseInt(amount.getText().toString());
        Checkout checkout = new Checkout();
        checkout.setImage(R.drawable.ic_account_balance_black_24dp);
        final Activity activity = this;

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("description", "order#123");
            jsonObject.put("currency", "INR");
            jsonObject.put("amount", paymentamount*100);
            checkout.open(activity,jsonObject);

        } catch (Exception e) {

        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.e( "onPaymentSuccess: ",s );
        Toast.makeText(this, "Payment Successful....", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        Log.e( "onPaymentFail: ",String.valueOf(i) );
        Toast.makeText(this, "Payment NOT Successful....", Toast.LENGTH_SHORT).show();
    }
}
