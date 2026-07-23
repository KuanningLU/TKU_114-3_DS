public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("===== 初始播放清單 =====");
        playlist.printPlaylist();

        System.out.println("\n===== 新增歌曲 =====");

        playlist.addSong("S001", "晴天");
        playlist.addSong("S002", "告白氣球");
        playlist.addSong("S003", "稻香");
        playlist.addSong("S004", "夜曲");

        playlist.printPlaylist();

        System.out.println("\n===== 測試重複歌曲代碼 =====");

        playlist.addSong("S002", "不能說的秘密");
        playlist.printPlaylist();

        System.out.println("\n===== 搜尋歌曲 =====");

        PlaylistNode result = playlist.search("S003");

        if (result != null) {
            System.out.println("找到歌曲：" + result);
        } else {
            System.out.println("找不到歌曲。");
        }

        result = playlist.search("S999");

        if (result != null) {
            System.out.println("找到歌曲：" + result);
        } else {
            System.out.println("找不到歌曲代碼：S999");
        }

        System.out.println("\n===== 刪除第一首歌曲 =====");

        playlist.removeSong("S001");
        playlist.printPlaylist();

        System.out.println("\n===== 刪除中間歌曲 =====");

        playlist.removeSong("S003");
        playlist.printPlaylist();

        System.out.println("\n===== 刪除最後一首歌曲 =====");

        playlist.removeSong("S004");
        playlist.printPlaylist();

        System.out.println("\n===== 刪除不存在的歌曲 =====");

        playlist.removeSong("S999");
        playlist.printPlaylist();

        System.out.println("\n===== 刪除剩餘歌曲 =====");

        playlist.removeSong("S002");
        playlist.printPlaylist();

        System.out.println("\n===== 測試空播放清單刪除 =====");

        playlist.removeSong("S001");
        playlist.printPlaylist();
    }
}