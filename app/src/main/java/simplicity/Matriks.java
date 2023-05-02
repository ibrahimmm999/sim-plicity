package simplicity;

public class Matriks {

    private int rows;
    private int columns;
    private int[][] matriks;
    private boolean[][] matrix;

    public Matriks(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        int r, c;

        matriks = new int[rows][columns];

        for (r = 0; r < rows; r++) {
            for (c = 0; c < columns; c++) {
                matriks[r][c] = 0;
            }
        }
    }

    public void printMatriks() {
        int r, c;

        for (r = 0; r < rows; r++) {
            System.out.println(" ");
            for (c = 0; c < columns; c++) {
                System.out.print(matriks[r][c] + " ");
            }
        }
    }

    public void setMatriks(Point kiriAtas, Point kananBawah, boolean beAvail) {
        int x, y, value;
        int x1 = kiriAtas.getX();
        int x2 = kananBawah.getX();
        int y1 = kiriAtas.getY();
        int y2 = kananBawah.getY();

        if (beAvail == true) {
            value = 0;
        } else {
            value = 1;
        }
        for (y = y1; y <= y2; y++) {
            for (x = x1; x <= x2; x++) {
                matriks[y][x] = value;
            }
        }
    }
    
    public boolean setDenahRumah(Point kiriAtas, int kodeRuangan) {
        // return true jika denah rumah berhasil dibuat, false jika denah gagal dibuat
        int x, y, x1, y1;
        boolean empty = true;
        x1 = kiriAtas.getX();
        y1 = kiriAtas.getY();
        
        // cek seluruh bagian yang ingin di-cover belum memiliki kode ruangan tertentu
        for (y = y1; y <= (y1 + 5); y++) {
            for (x = x1; x <= (x1 + 5); x++) {
                if (matriks[y][x] != 0) {
                    empty = false;
                }
            }
        } 
        if (empty == true) {
            for (y = y1; y <= (y1 + 5); y++) {
                for (x = x1; x <= (x1 + 5); x++) {
                    matriks[y][x] = kodeRuangan;
                }
            }    
        }
        return empty;   
    }

    public boolean checkAvailability(Point kiriAtas, Point kananBawah) {
        // return true apabila available
        boolean avail = true;
        int x, y;
        int x1 = kiriAtas.getX();
        int x2 = kananBawah.getX();
        int y1 = kiriAtas.getY();
        int y2 = kananBawah.getY();

        for (y = y1; y <= y2; y++) {
            for (x = x1; x <= x2; x++) {
                if (matriks[y][x] == 1) {
                    avail = false;
                }
            }
        }

        return avail;
    }

    public boolean cekUkuran(int[] ukuran) {
        int panjangBarang = ukuran[0];
        int lebarBarang = ukuran[1];

        for (int i = 0; i <= rows - panjangBarang; i++) {
            for (int j = 0; j <= columns - lebarBarang; j++) {
                boolean bisaDitempati = true;
                for (int k = i; k < i + panjangBarang; k++) {
                    for (int l = j; l < j + lebarBarang; l++) {
                        if (matrix[k][l]) {
                            bisaDitempati = false;
                            break;
                        }
                    }
                    if (!bisaDitempati) {
                        break;
                    }
                }
                if (bisaDitempati) {
                    return true;
                }
            }
        }
        return false;
    }

    public Posisi cariPosisi(int[] ukuran) {
        int panjangBarang = ukuran[0];
        int lebarBarang = ukuran[1];

        for (int i = 0; i <= rows - panjangBarang; i++) {
            for (int j = 0; j <= columns - lebarBarang; j++) {
                boolean bisaDitempati = true;
                for (int k = i; k < i + panjangBarang; k++) {
                    for (int l = j; l < j + lebarBarang; l++) {
                        if (matrix[k][l]) {
                            bisaDitempati = false;
                            break;
                        }
                    }
                    if (!bisaDitempati) {
                        break;
                    }
                }
                if (bisaDitempati) {
                    Posisi posisiBarang = new Posisi(new Point(i, j),
                            new Point(i + panjangBarang - 1, j + lebarBarang - 1));
                    return posisiBarang;
                }
            }
        }
        return null;
    }

    public void tandaiPosisi(Posisi posisi) {
        int kiriAtasX = posisi.getKiriAtas().getX();
        int kiriAtasY = posisi.getKiriAtas().getY();
        int kananBawahX = posisi.getKananBawah().getX();
        int kananBawahY = posisi.getKananBawah().getY();

        for (int i = kiriAtasX; i <= kananBawahX; i++) {
            for (int j = kiriAtasY; j <= kananBawahY; j++) {
                matrix[i][j] = true;
            }
        }
    }

    public void pasangBarang(Non_Makanan barang, Posisi posisiBarang) {
        int kiriAtasX = posisiBarang.getKiriAtas().getX();
        int kiriAtasY = posisiBarang.getKiriAtas().getY();
        int kananBawahX = posisiBarang.getKananBawah().getX();
        int kananBawahY = posisiBarang.getKananBawah().getY();

        if (kiriAtasX < 0 || kiriAtasY < 0 || kananBawahX >= rows || kananBawahY >= columns) {
            System.out.println("Posisi di luar matriks");
            return;
        }

        for (int i = kiriAtasX; i <= kananBawahX; i++) {
            for (int j = kiriAtasY; j <= kananBawahY; j++) {
                if (matrix[i][j]) {
                    System.out.println("Posisi di dalam matriks sudah terisi");
                    return;
                }
            }
        }

        for (int i = kiriAtasX; i <= kananBawahX; i++) {
            for (int j = kiriAtasY; j <= kananBawahY; j++) {
                matrix[i][j] = true;
            }
        }
    }

}
