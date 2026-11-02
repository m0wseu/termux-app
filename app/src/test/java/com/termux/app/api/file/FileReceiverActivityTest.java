package com.termux.app.api.file;

import com.termux.app.api.file.FileReceiverActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class FileReceiverActivityTest {

    @Test
    public void testIsSharedTextAnUrl() {
        List<String> validUrls = new ArrayList<>();
        validUrls.add("http://example.com");
        validUrls.add("https://example.com");
        validUrls.add("https://example.com/path/parameter=foo");
        validUrls.add("magnet:?xt=urn:btih:d540fc48eb12f2833163eed6421d449dd8f1ce1f&dn=Ubuntu+desktop+19.04+%2864bit%29&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&tr=udp%3A%2F%2Ftracker.ccc.de%3A80");
        // Non-web URI schemes must be sent to termux-url-opener instead of the file editor (#3935).
        validUrls.add("sftp://example.com/file");
        validUrls.add("smb://server/share");
        validUrls.add("irc://irc.example.com/channel");
        validUrls.add("gopher://example.com");
        validUrls.add("mailto:user@example.com");
        validUrls.add("nfs://server/export");
        validUrls.add("nzblnk:?t=test");
        validUrls.add("rtmp://example.com/live");
        validUrls.add("jabber://user@example.com");
        for (String url : validUrls) {
            Assert.assertTrue(FileReceiverActivity.isSharedTextAnUrl(url));
        }

        List<String> invalidUrls = new ArrayList<>();
        invalidUrls.add("a test with example.com");
        invalidUrls.add("");
        invalidUrls.add(null);
        for (String url : invalidUrls) {
            Assert.assertFalse(FileReceiverActivity.isSharedTextAnUrl(url));
        }
    }

}
