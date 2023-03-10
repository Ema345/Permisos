package com.example.permisos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    // Banderas que indicarán si tenemos permisos
    private boolean tienePermisoCamara = false,
            tienePermisoAlmacenamiento = false,
            tienePermisoInternet= false,
            tienePermisoBluetooth = false;

    // Código de permiso, defínelo tú mismo
    private static final int CODIGO_PERMISOS_CAMARA = 1,
            CODIGO_PERMISOS_ALMACENAMIENTO = 2, CODIGO_PERMISOS_INTERNET=3, CODIGO_PERMISOS_BLUETOOTH=4;

    private void verificarYPedirPermisosDeCamara() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            permisoDeCamaraConcedido();
        } else {
            // Si no, entonces pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }

    private void verificarYPedirPermisosDeAlmacenamiento() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            permisoDeAlmacenamientoConcedido();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODIGO_PERMISOS_ALMACENAMIENTO);
        }
    }
    private void verificarYPedirPermisosDeInternet() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            permisoDeInternetConcedido();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.INTERNET},
                    CODIGO_PERMISOS_INTERNET);
        }
    }
    private void verificarYPedirPermisosDeBluetooth() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            permisoDeBluetoothConcedido();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH},
                    CODIGO_PERMISOS_BLUETOOTH);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoDeCamaraConcedido();
                } else {
                    permisoDeCamaraDenegado();
                }
                break;

            case CODIGO_PERMISOS_ALMACENAMIENTO:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoDeAlmacenamientoConcedido();
                } else {
                    permisoDeAlmacenamientoDenegado();
                }
                break;
            case CODIGO_PERMISOS_INTERNET:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoDeInternetConcedido();
                } else {
                    permisoDeInternetDenegado();
                }
                break;
            case CODIGO_PERMISOS_BLUETOOTH:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permisoDeBluetoothConcedido();
                } else {
                    permisoDeBluetoothDenegado();
                }
                break;
        }
    }

    private void permisoDeAlmacenamientoConcedido() {
        Toast.makeText(MainActivity.this, "El permiso para el almacenamiento está concedido", Toast.LENGTH_SHORT).show();
        tienePermisoAlmacenamiento = true;
    }

    private void permisoDeAlmacenamientoDenegado() {
        Toast.makeText(MainActivity.this, "El permiso para el almacenamiento está denegado", Toast.LENGTH_SHORT).show();
    }

    private void permisoDeCamaraConcedido() {
        Toast.makeText(MainActivity.this, "El permiso para la cámara está concedido", Toast.LENGTH_SHORT).show();
        tienePermisoCamara = true;
    }

    private void permisoDeCamaraDenegado() {
        Toast.makeText(MainActivity.this, "El permiso para la cámara está denegado", Toast.LENGTH_SHORT).show();
    }
    private void permisoDeInternetConcedido() {
        Toast.makeText(MainActivity.this, "El permiso para el internet está concedido", Toast.LENGTH_SHORT).show();
        tienePermisoInternet = true;
    }

    private void permisoDeInternetDenegado() {
        Toast.makeText(MainActivity.this, "El permiso para el internet está denegado", Toast.LENGTH_SHORT).show();
    }
    private void permisoDeBluetoothConcedido() {
        Toast.makeText(MainActivity.this, "El permiso para el bluetooth está concedido", Toast.LENGTH_SHORT).show();
        tienePermisoBluetooth = true;
    }

    private void permisoDeBluetoothDenegado() {
        Toast.makeText(MainActivity.this, "El permiso para el bluetooth está denegado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Algunos botones que, al ser tocados, van a pedir los permisos
        Button btnPermisoCamara = findViewById(R.id.PCamara),
                btnPermisoAlmacenamiento = findViewById(R.id.PAlmacenamiento),
                btnPermisoInternet = findViewById(R.id.PInternet),
                btnPermisoBluetooth = findViewById(R.id.PBluetooth);
        btnPermisoCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarYPedirPermisosDeCamara();
            }
        });

        btnPermisoAlmacenamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarYPedirPermisosDeAlmacenamiento();
            }
        });
        btnPermisoInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarYPedirPermisosDeInternet();
            }
        });
        btnPermisoBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarYPedirPermisosDeBluetooth();
            }
        });
    }
}
