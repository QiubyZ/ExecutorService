package qz.serviceexcutor;

import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import qz.serviceexcutor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        
        setContentView(binding.getRoot());

        binding.result.setMovementMethod(new ScrollingMovementMethod());
        binding.execute.setOnClickListener(
                (v) -> {
				    executeExecutorServices(8,100);
                    Toast.makeText(
                                    v.getRootView().getContext(),
                                    "Start Executors",
                                    Toast.LENGTH_LONG)
                            .show();
                });
    }

    void executeExecutorServices(int thread,int looper) {
		ExecutorService executors = Executors.newFixedThreadPool(thread);
        binding.result.setText(""); // Clear TextView
        for (int angka = 0; angka < looper; angka++) {
            Task task = new Task();
            task.setText("Executors: " + String.valueOf(angka));
            executors.execute(task);
        }
		
    }

    class Task implements Runnable {
        private String settext;

        @Override
        public void run() {
            try {
                Thread.sleep(300); // Thread Block
				
                runOnUiThread(
                        () -> {
                            binding.result.append(settext + "\n");
                        });

            } catch (Exception err) {

            }
            // TODO: Implement this method
        }

        void setText(String text) {
            this.settext = text;
        }
    }
}
