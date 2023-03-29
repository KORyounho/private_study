package chosun.our.myapplication;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import javax.crypto.Cipher;

        import android.annotation.SuppressLint;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;


public class ReginsterActivity extends AppCompatActivity {
    private FirebaseAuth mFirbaseAuth; // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText et_id, et_pass, et_email;
    private Button register;
    private ArrayAdapter adapter;
    private Spinner spinner;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reginster);

        mFirbaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("finger_car");
        et_email = findViewById(R.id.emailTexT);
        et_pass = findViewById(R.id.id_pass);
        et_id = findViewById(R.id.id_user);
        register = (Button)findViewById(R.id.member_register);
        spinner = (Spinner)findViewById(R.id.User_Type);
        adapter = ArrayAdapter.createFromResource(this, R.array.user_identify, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = et_email.getText().toString();
                String inPass = et_pass.getText().toString();
                String strId = et_id.getText().toString();

                mFirbaseAuth.createUserWithEmailAndPassword(strEmail, inPass).addOnCompleteListener(ReginsterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirbaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(inPass);

                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(ReginsterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReginsterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}