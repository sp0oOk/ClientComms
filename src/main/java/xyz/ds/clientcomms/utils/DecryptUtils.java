package xyz.ds.clientcomms.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;

public class DecryptUtils {

    private final int[] c;
    private final int[] a;
    private final boolean b;

    public DecryptUtils(byte[] byArray, boolean bl) {
        this(ByteBuffer.wrap(byArray), bl);
    }

    public DecryptUtils(ByteBuffer byteBuffer, boolean bl) {
        if (bl) {
            if (byteBuffer.remaining() != 16 && byteBuffer.remaining() != 24) {
                throw new IllegalArgumentException("invalid key length: " + byteBuffer.remaining() + ", expected: " + 16 + " or " + 24);
            }
        } else if (byteBuffer.remaining() != 16) {
            throw new IllegalArgumentException("invalid key length: " + byteBuffer.remaining() + ", expected: " + 16);
        }
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        this.c = new int[]{byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt()};
        this.a = bl && byteBuffer.hasRemaining() ? new int[]{byteBuffer.getInt(), byteBuffer.getInt()} : null;
        this.b = bl;
    }

    public int a() {
        int n = 16;
        if (this.a != null) {
            n += 8;
        }
        return n;
    }

    public String c() {
        return Base64.getEncoder().encodeToString(this.b());
    }

    public byte[] b() {
        int n = this.a();
        byte[] byArray = new byte[n];
        ByteBuffer byteBuffer = ByteBuffer.wrap(byArray);
        this.a(byteBuffer);
        return byArray;
    }

    private void a(ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.putInt(this.c[0]);
        byteBuffer.putInt(this.c[1]);
        byteBuffer.putInt(this.c[2]);
        byteBuffer.putInt(this.c[3]);
        if (this.a != null) {
            byteBuffer.putInt(this.a[0]);
            byteBuffer.putInt(this.a[1]);
        }
    }

    public byte[] b(byte[] byArray, boolean bl) {
        ByteBuffer byteBuffer = this.a(ByteBuffer.wrap(byArray), bl);
        byte[] byArray2 = new byte[byteBuffer.remaining()];
        byteBuffer.get(byArray2);
        return byArray2;
    }

    public ByteBuffer a(ByteBuffer byteBuffer, boolean bl) {
        int n;
        int n2 = byteBuffer.remaining();
        int n3 = 0;
        if (n2 > 0) {
            n3 = 8 - (n2 & 7);
            if (!bl) {
                if (n3 != 8) {
                    throw new IllegalArgumentException("input not aligned to block size of 8");
                }
                n3 = 0;
            }
        }
        int n4 = n2 + n3;
        ByteBuffer byteBuffer2 = byteBuffer.isDirect() ? ByteBuffer.allocateDirect(n4) : ByteBuffer.allocate(n4);
        byteBuffer2.order(ByteOrder.BIG_ENDIAN);
        byteBuffer2.put(byteBuffer.duplicate());
        for (n = 0; n < n3; ++n) {
            byteBuffer2.put((byte)n3);
        }
        byteBuffer2.flip();
        n = 0;
        int n5 = 0;
        if (this.a != null) {
            n = this.a[0];
            n5 = this.a[1];
        }
        int n6 = n4 >>> 3;
        for (int i = 0; i < n6; ++i) {
            int n7 = i << 3;
            int n8 = byteBuffer2.getInt(n7);
            int n9 = byteBuffer2.getInt(n7 + 4);
            if (this.b) {
                n8 ^= n;
                n9 ^= n5;
            }
            int n10 = 0;
            for (int j = 0; j < 32; ++j) {
                n9 += ((n8 += (n9 << 4 ^ n9 >>> 5) + n9 ^ n10 + this.c[n10 & 3]) << 4 ^ n8 >>> 5) + n8 ^ (n10 -= 1640531527) + this.c[n10 >>> 11 & 3];
            }
            n = n8;
            n5 = n9;
            byteBuffer2.putInt(n7, n8);
            byteBuffer2.putInt(n7 + 4, n9);
        }
        return byteBuffer2;
    }

    public byte[] a(byte[] byArray, boolean bl) {
        ByteBuffer byteBuffer = this.b(ByteBuffer.wrap(byArray), bl);
        byte[] byArray2 = new byte[byteBuffer.remaining()];
        byteBuffer.get(byArray2);
        return byArray2;
    }

    public ByteBuffer b(ByteBuffer byteBuffer, boolean bl) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5 = byteBuffer.remaining();
        if ((n5 & 7) != 0) {
            throw new IllegalArgumentException("input not aligned to block size of 8");
        }
        ByteBuffer byteBuffer2 = byteBuffer.isDirect() ? ByteBuffer.allocateDirect(n5) : ByteBuffer.allocate(n5);
        byteBuffer2.order(ByteOrder.BIG_ENDIAN);
        byteBuffer2.put(byteBuffer.duplicate());
        byteBuffer2.flip();
        int n6 = 0;
        int n7 = 0;
        if (this.a != null) {
            n6 = this.a[0];
            n7 = this.a[1];
        }
        int n8 = n5 >>> 3;
        for (n4 = 0; n4 < n8; ++n4) {
            n3 = n4 << 3;
            n2 = byteBuffer2.getInt(n3);
            n = byteBuffer2.getInt(n3 + 4);
            int n9 = n2;
            int n10 = n;
            int n11 = -957401312;
            for (int i = 0; i < 32; ++i) {
                n2 -= ((n -= (n2 << 4 ^ n2 >>> 5) + n2 ^ n11 + this.c[n11 >>> 11 & 3]) << 4 ^ n >>> 5) + n ^ (n11 += 1640531527) + this.c[n11 & 3];
            }
            if (this.b) {
                n2 ^= n6;
                n ^= n7;
            }
            n6 = n9;
            n7 = n10;
            byteBuffer2.putInt(n3, n2);
            byteBuffer2.putInt(n3 + 4, n);
        }
        if (bl && n5 > 0) {
            n4 = byteBuffer2.get(n5 - 1) & 0xFF;
            n3 = 0;
            if (n4 > 0 && n4 <= 8) {
                for (n2 = 2; n2 <= n4; ++n2) {
                    n = byteBuffer2.get(n5 - n2) & 0xFF;
                    if (n4 == n) continue;
                    n3 = 1;
                    break;
                }
            } else {
                n3 = 1;
            }
            if (n3 != 0) {
                throw new IllegalArgumentException("invalid padding");
            }
            byteBuffer2.limit(byteBuffer2.limit() - n4);
            byteBuffer2 = byteBuffer2.slice();
        }
        return byteBuffer2;
    }
}