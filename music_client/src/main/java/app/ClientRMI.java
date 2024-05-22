package app;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.AlbumDAO;
import dao.SongDAO;
import entities.Song;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            // Tạo kết nối tới registry RMI
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2020); // Sử dụng localhost và cổng 1099 (cổng mặc định của RMI)

            // Lấy tham chiếu đến các đối tượng DAO từ registry
            SongDAO songDao = (SongDAO) registry.lookup("SongDao");
            AlbumDAO albumDao = (AlbumDAO) registry.lookup("AlbumDao");

            Scanner scanner = new Scanner(System.in);

            int option = 0;
            while (option != 4) {
                // Hiển thị menu chức năng
                System.out.println("Chọn chức năng:");
                System.out.println("1. Cập nhật tên của bài hát.");
                System.out.println("2. Hiển thị danh sách bài hát theo album.");
                System.out.println("3. Thống kê số album theo thể loại.");
                System.out.println("4. Thoát.");
                System.out.print("Nhập lựa chọn của bạn: ");
                
                try {
                    option = scanner.nextInt();
                    scanner.nextLine(); // Đọc và loại bỏ dòng trống sau khi đọc số nguyên
                } catch (InputMismatchException e) {
                    System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
                    scanner.nextLine(); // Đọc và loại bỏ dữ liệu không hợp lệ từ bộ đệm
                    continue; // Bỏ qua các lệnh còn lại trong vòng lặp và tiếp tục vòng lặp
                }

                switch (option) {
                    case 1:
                        // Update tên của bài hát
                        System.out.print("Nhập ID của bài hát cần cập nhật: ");
                        String songIdToUpdate = scanner.nextLine();
                        System.out.print("Nhập tên mới của bài hát: ");
                        String newSongName = scanner.nextLine();
                        boolean success = songDao.updateNameOfSong(songIdToUpdate, newSongName);

                        // Kiểm tra kết quả và in ra thông báo tương ứng
                        if (success) {
                            System.out.println("Tên của bài hát đã được cập nhật thành công.");
                        } else {
                            System.out.println("Cập nhật tên của bài hát không thành công.");
                        }
                        break;

                    case 2:
                        // Lấy danh sách bài hát theo album
                        System.out.print("Nhập tiêu đề của album: ");
                        String albumTitle = scanner.nextLine();
                        int albumYear;
                        while (true) {
                            try {
                                System.out.print("Nhập năm phát hành của album: ");
                                albumYear = scanner.nextInt();
                                scanner.nextLine(); // Đọc và loại bỏ dòng trống sau khi đọc số nguyên
                                break; // Thoát khỏi vòng lặp nếu nhập thành công
                            } catch (InputMismatchException e) {
                                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
                                scanner.nextLine(); // Đọc và loại bỏ dữ liệu không hợp lệ từ bộ đệm
                            }
                        }

                        List<Song> songs = songDao.listSongByAlbum(albumTitle, albumYear);

                        // Kiểm tra xem danh sách bài hát có rỗng hay không và in ra thông tin của từng bài hát
                        if (songs != null && !songs.isEmpty()) {
                            System.out.println("Danh sách bài hát thuộc album '" + albumTitle + "' năm " + albumYear + ":");
                            for (Song song : songs) {
                                System.out.println("Thông tin của bài hát:");
                                System.out.println("ID: " + song.getId());
                                System.out.println("Tên: " + song.getName());
                                System.out.println("Thời lượng: " + song.getRuntime());
                                System.out.println("Lời bài hát: " + song.getLyric());
                                System.out.println("Link tải: " + song.getFileLink());
                                System.out.println("-----------------------");
                            }
                        } else {
                            System.out.println("Không tìm thấy bài hát thuộc album '" + albumTitle + "' năm " + albumYear + ".");
                        }
                        break;

                    case 3:
                        // Thống kê số album theo thể loại
                        Map<String, Integer> numberOfAlbumsByGenre = albumDao.getNumberOfAlbumsByGenre();

                        // Hiển thị kết quả
                        if (numberOfAlbumsByGenre != null && !numberOfAlbumsByGenre.isEmpty()) {
                            System.out.println("Thống kê số album theo từng thể loại:");
                            numberOfAlbumsByGenre.forEach((genre, count) -> System.out.println("Thể loại: " + genre + ", Số album: " + count));
                        } else {
                            System.out.println("Không có dữ liệu thống kê.");
                        }
                        break;

                    case 4:
                        System.out.println("Ứng dụng đã thoát.");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
