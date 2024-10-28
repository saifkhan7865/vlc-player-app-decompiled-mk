package org.bouncycastle.pqc.crypto.falcon;

import androidx.car.app.hardware.common.CarUnit;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.handler.codec.http2.Http2CodecUtil;

class FalconKeyGen {
    final int DEPTH_INT_FG = 4;
    final int[] MAX_BL_LARGE = {2, 2, 5, 7, 12, 21, 40, 78, 157, 308};
    final int[] MAX_BL_SMALL = {1, 1, 2, 2, 4, 7, 14, 27, 53, 106, 209};
    private short[] REV10 = {0, 512, Http2CodecUtil.MAX_WEIGHT, 768, 128, 640, 384, 896, 64, 576, 320, 832, 192, 704, 448, 960, 32, 544, 288, 800, 160, 672, 416, 928, 96, 608, 352, 864, 224, 736, 480, 992, 16, 528, 272, 784, 144, 656, 400, 912, 80, 592, 336, 848, 208, 720, 464, 976, 48, 560, 304, 816, 176, 688, 432, 944, 112, 624, 368, 880, 240, 752, 496, 1008, 8, 520, 264, 776, 136, 648, 392, 904, 72, 584, 328, 840, 200, 712, 456, 968, 40, 552, 296, 808, 168, 680, 424, 936, 104, 616, 360, 872, 232, 744, 488, 1000, 24, 536, 280, 792, 152, 664, 408, 920, 88, 600, 344, 856, 216, 728, 472, 984, 56, 568, 312, 824, 184, 696, 440, 952, 120, 632, 376, 888, 248, 760, 504, 1016, 4, 516, 260, 772, 132, 644, 388, 900, 68, 580, 324, 836, 196, 708, 452, 964, 36, 548, 292, 804, 164, 676, 420, 932, 100, 612, 356, 868, 228, 740, 484, 996, 20, 532, 276, 788, 148, 660, 404, 916, 84, 596, 340, 852, 212, 724, 468, 980, 52, 564, 308, 820, 180, 692, 436, 948, 116, 628, 372, 884, 244, 756, 500, 1012, 12, 524, 268, 780, 140, 652, 396, 908, 76, 588, 332, 844, 204, 716, 460, 972, 44, 556, 300, 812, 172, 684, 428, 940, 108, 620, 364, 876, 236, 748, 492, 1004, 28, 540, 284, 796, 156, 668, 412, 924, 92, 604, 348, 860, 220, 732, 476, 988, 60, 572, 316, 828, 188, 700, 444, 956, 124, 636, 380, 892, 252, 764, 508, 1020, 2, 514, 258, 770, 130, 642, 386, 898, 66, 578, 322, 834, 194, 706, 450, 962, 34, 546, 290, 802, 162, 674, 418, 930, 98, 610, 354, 866, 226, 738, 482, 994, 18, 530, 274, 786, 146, 658, 402, 914, 82, 594, 338, 850, 210, 722, 466, 978, 50, 562, 306, 818, 178, 690, 434, 946, 114, 626, 370, 882, 242, 754, 498, 1010, 10, 522, 266, 778, 138, 650, 394, 906, 74, 586, 330, 842, 202, 714, 458, 970, 42, 554, 298, 810, 170, 682, 426, 938, 106, 618, 362, 874, 234, 746, 490, 1002, 26, 538, 282, 794, 154, 666, 410, 922, 90, 602, 346, 858, 218, 730, 474, 986, 58, 570, 314, 826, 186, 698, 442, 954, 122, 634, 378, 890, 250, 762, 506, 1018, 6, 518, 262, 774, 134, 646, 390, 902, 70, 582, 326, 838, 198, 710, 454, 966, 38, 550, 294, 806, 166, 678, 422, 934, 102, 614, 358, 870, 230, 742, 486, 998, 22, 534, 278, 790, 150, 662, 406, 918, 86, 598, 342, 854, 214, 726, 470, 982, 54, 566, 310, 822, 182, 694, 438, 950, 118, 630, 374, 886, 246, 758, 502, 1014, 14, 526, 270, 782, 142, 654, 398, 910, 78, 590, 334, 846, 206, 718, 462, 974, 46, 558, 302, 814, 174, 686, 430, 942, 110, 622, 366, 878, 238, 750, 494, 1006, 30, 542, 286, 798, 158, 670, 414, 926, 94, 606, 350, 862, 222, 734, 478, 990, 62, 574, 318, 830, 190, 702, 446, 958, 126, 638, 382, 894, 254, 766, 510, 1022, 1, 513, 257, 769, 129, 641, 385, 897, 65, 577, 321, 833, 193, 705, 449, 961, 33, 545, 289, 801, 161, 673, 417, 929, 97, 609, 353, 865, 225, 737, 481, 993, 17, 529, 273, 785, 145, 657, 401, 913, 81, 593, 337, 849, 209, 721, 465, 977, 49, 561, 305, 817, 177, 689, 433, 945, 113, 625, 369, 881, 241, 753, 497, 1009, 9, 521, 265, 777, 137, 649, 393, 905, 73, 585, 329, 841, 201, 713, 457, 969, 41, 553, 297, 809, 169, 681, 425, 937, 105, 617, 361, 873, 233, 745, 489, 1001, 25, 537, 281, 793, 153, 665, 409, 921, 89, 601, 345, 857, 217, 729, 473, 985, 57, 569, 313, 825, 185, 697, 441, 953, 121, 633, 377, 889, 249, 761, 505, 1017, 5, 517, 261, 773, 133, 645, 389, 901, 69, 581, 325, 837, 197, 709, 453, 965, 37, 549, 293, 805, 165, 677, 421, 933, 101, 613, 357, 869, 229, 741, 485, 997, 21, 533, 277, 789, 149, 661, 405, 917, 85, 597, 341, 853, 213, 725, 469, 981, 53, 565, 309, 821, 181, 693, 437, 949, 117, 629, 373, 885, 245, 757, 501, 1013, 13, 525, 269, 781, 141, 653, 397, 909, 77, 589, 333, 845, 205, 717, 461, 973, 45, 557, 301, 813, 173, 685, 429, 941, 109, 621, 365, 877, 237, 749, 493, 1005, 29, 541, 285, 797, 157, 669, 413, 925, 93, 605, 349, 861, 221, 733, 477, 989, 61, 573, 317, 829, 189, 701, 445, 957, 125, 637, 381, 893, 253, 765, 509, 1021, 3, 515, 259, 771, 131, 643, 387, 899, 67, 579, 323, 835, 195, 707, 451, 963, 35, 547, 291, 803, 163, 675, 419, 931, 99, 611, 355, 867, 227, 739, 483, 995, 19, 531, 275, 787, 147, 659, 403, 915, 83, 595, 339, 851, 211, 723, 467, 979, 51, 563, 307, 819, 179, 691, 435, 947, 115, 627, 371, 883, 243, 755, 499, 1011, 11, 523, 267, 779, 139, 651, 395, 907, 75, 587, 331, 843, 203, 715, 459, 971, 43, 555, 299, 811, 171, 683, 427, 939, 107, 619, 363, 875, 235, 747, 491, 1003, 27, 539, 283, 795, 155, 667, 411, 923, 91, 603, 347, 859, 219, 731, 475, 987, 59, 571, 315, 827, 187, 699, 443, 955, 123, 635, 379, 891, 251, 763, 507, 1019, 7, 519, 263, 775, 135, 647, 391, 903, 71, 583, 327, 839, 199, 711, 455, 967, 39, 551, 295, 807, 167, 679, 423, 935, 103, 615, 359, 871, 231, 743, 487, 999, 23, 535, 279, 791, 151, 663, 407, 919, 87, 599, 343, 855, 215, 727, 471, 983, 55, 567, 311, 823, 183, 695, 439, 951, 119, 631, 375, 887, 247, 759, 503, 1015, 15, 527, 271, 783, 143, 655, 399, 911, 79, 591, 335, 847, 207, 719, 463, 975, 47, 559, 303, 815, 175, 687, 431, 943, 111, 623, 367, 879, 239, 751, 495, 1007, 31, 543, 287, 799, 159, 671, 415, 927, 95, 607, 351, 863, 223, 735, 479, 991, 63, 575, 319, 831, 191, 703, 447, 959, 127, 639, 383, 895, Http2CodecUtil.MAX_UNSIGNED_BYTE, 767, 511, 1023};
    final int[] bitlength_avg = {4, 11, 24, 50, 102, CarUnit.LITER, TypedValues.CycleType.TYPE_CURVE_FIT, 794, 1577, 3138, 6308};
    final int[] bitlength_std = {0, 1, 1, 1, 1, 2, 4, 5, 8, 13, 25};
    FalconCodec codec = new FalconCodec();
    FalconFFT fft = new FalconFFT();
    FPREngine fpr = new FPREngine();
    final long[] gauss_1024_12289 = {1283868770400643928L, 6416574995475331444L, 4078260278032692663L, 2353523259288686585L, 1227179971273316331L, 575931623374121527L, 242543240509105209L, 91437049221049666L, 30799446349977173L, 9255276791179340L, 2478152334826140L, 590642893610164L, 125206034929641L, 23590435911403L, 3948334035941L, 586753615614L, 77391054539L, 9056793210L, 940121950, 86539696, 7062824, 510971, 32764, 1862, 94, 4, 0};
    FalconSmallPrimeList primes = new FalconSmallPrimeList();
    FalconVrfy vrfy = new FalconVrfy();

    FalconKeyGen() {
    }

    private static int mkn(int i) {
        return 1 << i;
    }

    private long toUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }

    /* access modifiers changed from: package-private */
    public long get_rng_u64(SHAKE256 shake256) {
        byte[] bArr = new byte[8];
        shake256.inner_shake256_extract(bArr, 0, 8);
        return ((((long) bArr[7]) & 255) << 56) | (((long) bArr[0]) & 255) | ((((long) bArr[1]) & 255) << 8) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[6]) & 255) << 48);
    }

    /* access modifiers changed from: package-private */
    public void keygen(SHAKE256 shake256, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, byte[] bArr4, int i4, short[] sArr, int i5, int i6) {
        int i7;
        int i8;
        byte b;
        SHAKE256 shake2562 = shake256;
        byte[] bArr5 = bArr;
        int i9 = i;
        byte[] bArr6 = bArr2;
        int i10 = i2;
        int i11 = i6;
        int mkn = mkn(i6);
        short[] sArr2 = sArr;
        while (true) {
            FalconFPR[] falconFPRArr = new FalconFPR[(mkn * 3)];
            poly_small_mkgauss(shake2562, bArr5, i9, i11);
            poly_small_mkgauss(shake2562, bArr6, i10, i11);
            int i12 = 1 << (this.codec.max_fg_bits[i11] - 1);
            int i13 = 0;
            int i14 = 0;
            while (true) {
                if (i14 < mkn) {
                    byte b2 = bArr5[i9 + i14];
                    if (b2 >= i12 || b2 <= (i8 = -i12) || (b = bArr6[i10 + i14]) >= i12 || b <= i8) {
                        break;
                    }
                    i14++;
                } else if (i12 >= 0) {
                    int poly_small_sqnorm = poly_small_sqnorm(bArr5, i9, i11);
                    int poly_small_sqnorm2 = poly_small_sqnorm(bArr6, i10, i11);
                    if ((((long) ((-((poly_small_sqnorm | poly_small_sqnorm2) >>> 31)) | (poly_small_sqnorm + poly_small_sqnorm2))) & 4294967295L) < 16823) {
                        int i15 = mkn + mkn;
                        FalconFPR[] falconFPRArr2 = falconFPRArr;
                        int i16 = i6;
                        poly_small_to_fp(falconFPRArr2, 0, bArr, i, i16);
                        poly_small_to_fp(falconFPRArr2, mkn, bArr2, i2, i16);
                        this.fft.FFT(falconFPRArr, 0, i11);
                        this.fft.FFT(falconFPRArr, mkn, i11);
                        FalconFPR[] falconFPRArr3 = falconFPRArr;
                        this.fft.poly_invnorm2_fft(falconFPRArr2, i15, falconFPRArr, 0, falconFPRArr, mkn, i6);
                        this.fft.poly_adj_fft(falconFPRArr3, 0, i11);
                        this.fft.poly_adj_fft(falconFPRArr3, mkn, i11);
                        this.fft.poly_mulconst(falconFPRArr3, 0, this.fpr.fpr_q, i11);
                        this.fft.poly_mulconst(falconFPRArr3, mkn, this.fpr.fpr_q, i11);
                        FalconFPR[] falconFPRArr4 = falconFPRArr3;
                        FalconFPR[] falconFPRArr5 = falconFPRArr3;
                        int i17 = i15;
                        int i18 = i6;
                        this.fft.poly_mul_autoadj_fft(falconFPRArr4, 0, falconFPRArr5, i17, i18);
                        this.fft.poly_mul_autoadj_fft(falconFPRArr4, mkn, falconFPRArr5, i17, i18);
                        this.fft.iFFT(falconFPRArr3, 0, i11);
                        this.fft.iFFT(falconFPRArr3, mkn, i11);
                        FalconFPR falconFPR = this.fpr.fpr_zero;
                        for (int i19 = 0; i19 < mkn; i19++) {
                            FPREngine fPREngine = this.fpr;
                            FalconFPR fpr_add = fPREngine.fpr_add(falconFPR, fPREngine.fpr_sqr(falconFPRArr3[i19]));
                            FPREngine fPREngine2 = this.fpr;
                            falconFPR = fPREngine2.fpr_add(fpr_add, fPREngine2.fpr_sqr(falconFPRArr3[mkn + i19]));
                        }
                        FPREngine fPREngine3 = this.fpr;
                        if (fPREngine3.fpr_lt(falconFPR, fPREngine3.fpr_bnorm_max)) {
                            short[] sArr3 = new short[(mkn * 2)];
                            if (sArr2 == null) {
                                i13 = mkn;
                                sArr2 = sArr3;
                                i7 = 0;
                            } else {
                                i7 = i5;
                            }
                            int i20 = mkn;
                            short[] sArr4 = sArr3;
                            int i21 = i11;
                            if (this.vrfy.compute_public(sArr2, i7, bArr, i, bArr2, i2, i6, sArr4, i13) != 0) {
                                if (solve_NTRU(i6, bArr3, i3, bArr4, i4, bArr, i, bArr2, i2, (1 << (this.codec.max_FG_bits[i21] - 1)) - 1, new int[(i21 > 2 ? i20 * 28 : i20 * 84)], 0) != 0) {
                                    return;
                                }
                            }
                            i9 = i;
                            bArr6 = bArr2;
                            i10 = i2;
                            i11 = i6;
                            mkn = i20;
                        }
                    }
                }
            }
            i10 = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public void make_fg(int[] iArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4, int i5, int i6) {
        int i7 = i5;
        int mkn = mkn(i4);
        int i8 = i + mkn;
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i9 = falconSmallPrimeArr[0].p;
        for (int i10 = 0; i10 < mkn; i10++) {
            iArr[i + i10] = modp_set(bArr[i2 + i10], i9);
            iArr[i8 + i10] = modp_set(bArr2[i3 + i10], i9);
        }
        if (i7 != 0 || i6 == 0) {
            int i11 = 0;
            while (i11 < i7) {
                int i12 = i11 + 1;
                make_fg_step(iArr, i, i4 - i11, i11, i11 != 0 ? 1 : 0, (i12 < i7 || i6 != 0) ? 1 : 0);
                i11 = i12;
            }
            return;
        }
        int i13 = falconSmallPrimeArr[0].p;
        int modp_ninv31 = modp_ninv31(i13);
        int i14 = i8 + mkn;
        int i15 = i14 + mkn;
        int i16 = falconSmallPrimeArr[0].g;
        int[] iArr2 = iArr;
        int[] iArr3 = iArr;
        int i17 = i4;
        modp_mkgm2(iArr2, i14, iArr3, i15, i17, i16, i13, modp_ninv31);
        int i18 = i14;
        int i19 = i13;
        int i20 = modp_ninv31;
        modp_NTT2(iArr2, i, iArr3, i18, i17, i19, i20);
        modp_NTT2(iArr2, i8, iArr3, i18, i17, i19, i20);
    }

    /* access modifiers changed from: package-private */
    public void make_fg_step(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int[] iArr2 = iArr;
        int i13 = i;
        int i14 = 1 << i2;
        int i15 = i14 >> 1;
        int[] iArr3 = this.MAX_BL_SMALL;
        int i16 = iArr3[i3];
        int i17 = iArr3[i3 + 1];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i18 = i15 * i17;
        int i19 = i13 + i18;
        int i20 = i19 + i18;
        int i21 = i14 * i16;
        int i22 = i20 + i21;
        int i23 = i22 + i21;
        int i24 = i23 + i14;
        int i25 = i24 + i14;
        System.arraycopy(iArr2, i13, iArr2, i20, i14 * 2 * i16);
        int i26 = 0;
        while (i26 < i16) {
            int i27 = falconSmallPrimeArr[i26].p;
            int modp_ninv31 = modp_ninv31(i27);
            int modp_R2 = modp_R2(i27, modp_ninv31);
            int i28 = modp_ninv31;
            int i29 = i27;
            int i30 = i26;
            int i31 = i20;
            int i32 = i17;
            modp_mkgm2(iArr, i23, iArr, i24, i2, falconSmallPrimeArr[i26].g, i29, i28);
            int i33 = i31 + i30;
            int i34 = i33;
            int i35 = 0;
            while (i35 < i14) {
                iArr2[i25 + i35] = iArr2[i34];
                i35++;
                i34 += i16;
            }
            if (i4 == 0) {
                modp_NTT2(iArr, i25, iArr, i23, i2, i29, i28);
            }
            int i36 = i13 + i30;
            int i37 = i36;
            int i38 = 0;
            while (i38 < i15) {
                int i39 = i25 + (i38 << 1);
                int i40 = i28;
                int i41 = i29;
                iArr2[i37] = modp_montymul(modp_montymul(iArr2[i39], iArr2[i39 + 1], i41, i40), modp_R2, i41, i40);
                i38++;
                i37 += i32;
            }
            int i42 = i28;
            int i43 = modp_R2;
            int i44 = i29;
            int i45 = i32;
            if (i4 != 0) {
                i8 = i43;
                i6 = i42;
                i9 = i44;
                i7 = i45;
                modp_iNTT2_ext(iArr, i33, i16, iArr, i24, i2, i44, i6);
            } else {
                i7 = i45;
                i8 = i43;
                i6 = i42;
                i9 = i44;
            }
            int i46 = i22 + i30;
            int i47 = i46;
            int i48 = 0;
            while (i48 < i14) {
                iArr2[i25 + i48] = iArr2[i47];
                i48++;
                i47 += i16;
            }
            if (i4 == 0) {
                modp_NTT2(iArr, i25, iArr, i23, i2, i9, i6);
            }
            int i49 = i19 + i30;
            int i50 = i49;
            int i51 = 0;
            while (i51 < i15) {
                int i52 = i25 + (i51 << 1);
                int i53 = i6;
                int i54 = i9;
                iArr2[i50] = modp_montymul(modp_montymul(iArr2[i52], iArr2[i52 + 1], i54, i53), i8, i54, i53);
                i51++;
                i50 += i7;
            }
            int i55 = i6;
            int i56 = i9;
            int i57 = i7;
            if (i4 != 0) {
                i12 = i55;
                i11 = i56;
                i10 = i57;
                modp_iNTT2_ext(iArr, i46, i16, iArr, i24, i2, i56, i12);
            } else {
                i10 = i57;
                i12 = i55;
                i11 = i56;
            }
            if (i5 == 0) {
                int[] iArr4 = iArr;
                int i58 = i10;
                int[] iArr5 = iArr;
                int i59 = i24;
                int i60 = i2 - 1;
                int i61 = i11;
                int i62 = i12;
                modp_iNTT2_ext(iArr4, i36, i58, iArr5, i59, i60, i61, i62);
                modp_iNTT2_ext(iArr4, i49, i58, iArr5, i59, i60, i61, i62);
            }
            i26 = i30 + 1;
            i20 = i31;
            i17 = i10;
        }
        int i63 = i20;
        int i64 = i17;
        int[] iArr6 = iArr;
        int i65 = i16;
        int i66 = i16;
        int i67 = i14;
        FalconSmallPrime[] falconSmallPrimeArr2 = falconSmallPrimeArr;
        int[] iArr7 = iArr;
        int i68 = i16;
        int i69 = i23;
        zint_rebuild_CRT(iArr6, i63, i65, i66, i67, falconSmallPrimeArr2, 1, iArr7, i69);
        zint_rebuild_CRT(iArr6, i22, i68, i68, i67, falconSmallPrimeArr2, 1, iArr7, i69);
        int i70 = i68;
        int i71 = i64;
        while (i70 < i71) {
            int i72 = falconSmallPrimeArr[i70].p;
            int modp_ninv312 = modp_ninv31(i72);
            int modp_R22 = modp_R2(i72, modp_ninv312);
            int i73 = i68;
            int modp_Rx = modp_Rx(i73, i72, modp_ninv312, modp_R22);
            int i74 = i73;
            int i75 = modp_R22;
            int i76 = modp_ninv312;
            int i77 = i72;
            int i78 = i71;
            modp_mkgm2(iArr, i23, iArr, i24, i2, falconSmallPrimeArr[i70].g, i72, i76);
            int i79 = i63;
            int i80 = 0;
            while (i80 < i14) {
                iArr2[i25 + i80] = zint_mod_small_signed(iArr, i79, i74, i77, i76, i75, modp_Rx);
                i80++;
                i79 += i74;
            }
            modp_NTT2(iArr, i25, iArr, i23, i2, i77, i76);
            int i81 = i13 + i70;
            int i82 = i81;
            int i83 = 0;
            while (i83 < i15) {
                int i84 = i25 + (i83 << 1);
                int i85 = i76;
                int i86 = i77;
                iArr2[i82] = modp_montymul(modp_montymul(iArr2[i84], iArr2[i84 + 1], i86, i85), i75, i86, i85);
                i83++;
                i82 += i78;
            }
            int i87 = i75;
            int i88 = i76;
            int i89 = i77;
            int i90 = i22;
            int i91 = 0;
            while (i91 < i14) {
                int i92 = i87;
                iArr2[i25 + i91] = zint_mod_small_signed(iArr, i90, i74, i89, i88, i92, modp_Rx);
                i91++;
                i90 += i74;
                i87 = i92;
                i88 = i88;
                i89 = i89;
            }
            int i93 = i87;
            int i94 = i88;
            int i95 = i89;
            modp_NTT2(iArr, i25, iArr, i23, i2, i95, i94);
            int i96 = i19 + i70;
            int i97 = i96;
            int i98 = 0;
            while (i98 < i15) {
                int i99 = i25 + (i98 << 1);
                int i100 = i94;
                int i101 = i95;
                iArr2[i97] = modp_montymul(modp_montymul(iArr2[i99], iArr2[i99 + 1], i101, i100), i93, i101, i100);
                i98++;
                i97 += i78;
            }
            int i102 = i94;
            int i103 = i95;
            if (i5 == 0) {
                int[] iArr8 = iArr;
                int i104 = i81;
                int i105 = i78;
                int[] iArr9 = iArr;
                int i106 = i24;
                int i107 = i102;
                int i108 = i2 - 1;
                int i109 = i103;
                int i110 = i107;
                modp_iNTT2_ext(iArr8, i104, i105, iArr9, i106, i108, i103, i110);
                modp_iNTT2_ext(iArr8, i96, i105, iArr9, i106, i108, i103, i110);
            }
            i70++;
            i68 = i74;
            i71 = i78;
        }
    }

    /* access modifiers changed from: package-private */
    public int mkgauss(SHAKE256 shake256, int i) {
        int i2 = 1 << (10 - i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            long j = get_rng_u64(shake256);
            int i5 = (int) (j >>> 63);
            int i6 = (int) (((j & Long.MAX_VALUE) - this.gauss_1024_12289[0]) >>> 63);
            long j2 = Long.MAX_VALUE & get_rng_u64(shake256);
            int i7 = 1;
            int i8 = 0;
            while (true) {
                long[] jArr = this.gauss_1024_12289;
                if (i7 >= jArr.length) {
                    break;
                }
                int i9 = ((int) ((j2 - jArr[i7]) >>> 63)) ^ 1;
                i8 |= (-((i6 ^ 1) & i9)) & i7;
                i6 |= i9;
                i7++;
            }
            i3 += ((-i5) ^ i8) + i5;
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public void modp_NTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_NTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    public void modp_NTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        int i7 = i5;
        if (i4 != 0) {
            int mkn = mkn(i4);
            int i8 = 1;
            int i9 = mkn;
            while (i8 < mkn) {
                int i10 = i9 >> 1;
                int i11 = 0;
                int i12 = 0;
                while (i11 < i8) {
                    int i13 = iArr2[i3 + i8 + i11];
                    int i14 = i + (i12 * i2);
                    int i15 = (i10 * i2) + i14;
                    int i16 = 0;
                    while (i16 < i10) {
                        int i17 = iArr[i14];
                        int modp_montymul = modp_montymul(iArr[i15], i13, i7, i6);
                        iArr[i14] = modp_add(i17, modp_montymul, i7);
                        iArr[i15] = modp_sub(i17, modp_montymul, i7);
                        i16++;
                        i14 += i2;
                        i15 += i2;
                    }
                    int i18 = i6;
                    i11++;
                    i12 += i9;
                }
                int i19 = i6;
                i8 <<= 1;
                i9 = i10;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int modp_R(int i) {
        return Integer.MIN_VALUE - i;
    }

    /* access modifiers changed from: package-private */
    public int modp_R2(int i, int i2) {
        int modp_R = modp_R(i);
        int modp_add = modp_add(modp_R, modp_R, i);
        int modp_montymul = modp_montymul(modp_add, modp_add, i, i2);
        int modp_montymul2 = modp_montymul(modp_montymul, modp_montymul, i, i2);
        int modp_montymul3 = modp_montymul(modp_montymul2, modp_montymul2, i, i2);
        int modp_montymul4 = modp_montymul(modp_montymul3, modp_montymul3, i, i2);
        int modp_montymul5 = modp_montymul(modp_montymul4, modp_montymul4, i, i2);
        return (modp_montymul5 + (i & (-(modp_montymul5 & 1)))) >>> 1;
    }

    /* access modifiers changed from: package-private */
    public int modp_Rx(int i, int i2, int i3, int i4) {
        int i5 = i - 1;
        int modp_R = modp_R(i2);
        int i6 = 0;
        while (true) {
            int i7 = 1 << i6;
            if (i7 > i5) {
                return modp_R;
            }
            if ((i7 & i5) != 0) {
                modp_R = modp_montymul(modp_R, i4, i2, i3);
            }
            i4 = modp_montymul(i4, i4, i2, i3);
            i6++;
        }
    }

    /* access modifiers changed from: package-private */
    public int modp_add(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    /* access modifiers changed from: package-private */
    public int modp_div(int i, int i2, int i3, int i4, int i5) {
        int i6 = i3 - 2;
        for (int i7 = 30; i7 >= 0; i7--) {
            int modp_montymul = modp_montymul(i5, i5, i3, i4);
            i5 = modp_montymul ^ ((-(1 & (i6 >>> i7))) & (modp_montymul(modp_montymul, i2, i3, i4) ^ modp_montymul));
        }
        return modp_montymul(i, modp_montymul(i5, 1, i3, i4), i3, i4);
    }

    /* access modifiers changed from: package-private */
    public void modp_iNTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_iNTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    public void modp_iNTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        int i7 = i5;
        int i8 = i6;
        if (i4 != 0) {
            int mkn = mkn(i4);
            int i9 = mkn;
            int i10 = 1;
            while (i9 > 1) {
                i9 >>= 1;
                int i11 = i10 << 1;
                int i12 = 0;
                int i13 = 0;
                while (i12 < i9) {
                    int i14 = iArr2[i3 + i9 + i12];
                    int i15 = i + (i13 * i2);
                    int i16 = (i10 * i2) + i15;
                    int i17 = 0;
                    while (i17 < i10) {
                        int i18 = iArr[i15];
                        int i19 = iArr[i16];
                        iArr[i15] = modp_add(i18, i19, i7);
                        iArr[i16] = modp_montymul(modp_sub(i18, i19, i7), i14, i7, i8);
                        i17++;
                        i15 += i2;
                        i16 += i2;
                    }
                    i12++;
                    i13 += i11;
                }
                i10 = i11;
            }
            int i20 = 1 << (31 - i4);
            int i21 = i;
            int i22 = 0;
            while (i22 < mkn) {
                iArr[i21] = modp_montymul(iArr[i21], i20, i7, i8);
                i22++;
                i21 += i2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void modp_mkgm2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i5;
        int i8 = i6;
        int mkn = mkn(i3);
        int modp_R2 = modp_R2(i7, i8);
        int modp_montymul = modp_montymul(i4, modp_R2, i7, i8);
        for (int i9 = i3; i9 < 10; i9++) {
            modp_montymul = modp_montymul(modp_montymul, modp_montymul, i7, i8);
        }
        int modp_div = modp_div(modp_R2, modp_montymul, i5, i6, modp_R(i7));
        int i10 = 10 - i3;
        int modp_R = modp_R(i7);
        int i11 = modp_R;
        for (int i12 = 0; i12 < mkn; i12++) {
            short s = this.REV10[i12 << i10];
            iArr[i + s] = modp_R;
            iArr2[i2 + s] = i11;
            modp_R = modp_montymul(modp_R, modp_montymul, i7, i8);
            i11 = modp_montymul(i11, modp_div, i7, i8);
        }
    }

    /* access modifiers changed from: package-private */
    public int modp_montymul(int i, int i2, int i3, int i4) {
        long unsignedLong = toUnsignedLong(i) * toUnsignedLong(i2);
        int unsignedLong2 = ((int) ((unsignedLong + (((((long) i4) * unsignedLong) & toUnsignedLong(Integer.MAX_VALUE)) * ((long) i3))) >>> 31)) - i3;
        return unsignedLong2 + ((-(unsignedLong2 >>> 31)) & i3);
    }

    /* access modifiers changed from: package-private */
    public int modp_ninv31(int i) {
        int i2 = 2 - i;
        int i3 = i2 * (2 - (i * i2));
        int i4 = i3 * (2 - (i * i3));
        int i5 = i4 * (2 - (i * i4));
        return Integer.MAX_VALUE & (-(i5 * (2 - (i * i5))));
    }

    /* access modifiers changed from: package-private */
    public int modp_norm(int i, int i2) {
        return i - (i2 & (((i - ((i2 + 1) >>> 1)) >>> 31) - 1));
    }

    /* access modifiers changed from: package-private */
    public void modp_poly_rec_res(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = 1 << (i2 - 1);
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = (i7 << 1) + i;
            iArr[i + i7] = modp_montymul(modp_montymul(iArr[i8], iArr[i8 + 1], i3, i4), i5, i3, i4);
        }
    }

    /* access modifiers changed from: package-private */
    public int modp_set(int i, int i2) {
        return i + (i2 & (-(i >>> 31)));
    }

    /* access modifiers changed from: package-private */
    public int modp_sub(int i, int i2, int i3) {
        int i4 = i - i2;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    /* access modifiers changed from: package-private */
    public void poly_big_to_fp(FalconFPR[] falconFPRArr, int i, int[] iArr, int i2, int i3, int i4, int i5) {
        int i6 = i3;
        int mkn = mkn(i5);
        if (i6 == 0) {
            for (int i7 = 0; i7 < mkn; i7++) {
                falconFPRArr[i + i7] = this.fpr.fpr_zero;
            }
            return;
        }
        int i8 = i2;
        int i9 = 0;
        while (i9 < mkn) {
            int i10 = -(iArr[(i8 + i6) - 1] >>> 30);
            int i11 = i10 >>> 1;
            int i12 = i10 & 1;
            FalconFPR falconFPR = this.fpr.fpr_zero;
            FalconFPR falconFPR2 = this.fpr.fpr_one;
            int i13 = 0;
            while (i13 < i6) {
                int i14 = (iArr[i8 + i13] ^ i11) + i12;
                i12 = i14 >>> 31;
                int i15 = i14 & Integer.MAX_VALUE;
                FPREngine fPREngine = this.fpr;
                falconFPR = fPREngine.fpr_add(falconFPR, fPREngine.fpr_mul(fPREngine.fpr_of((long) (i15 - ((i15 << 1) & i10))), falconFPR2));
                i13++;
                FPREngine fPREngine2 = this.fpr;
                falconFPR2 = fPREngine2.fpr_mul(falconFPR2, fPREngine2.fpr_ptwo31);
            }
            falconFPRArr[i + i9] = falconFPR;
            i9++;
            i8 += i4;
        }
    }

    /* access modifiers changed from: package-private */
    public int poly_big_to_small(byte[] bArr, int i, int[] iArr, int i2, int i3, int i4) {
        int mkn = mkn(i4);
        for (int i5 = 0; i5 < mkn; i5++) {
            int zint_one_to_plain = zint_one_to_plain(iArr, i2 + i5);
            if (zint_one_to_plain < (-i3) || zint_one_to_plain > i3) {
                return 0;
            }
            bArr[i + i5] = (byte) zint_one_to_plain;
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void poly_small_mkgauss(SHAKE256 shake256, byte[] bArr, int i, int i2) {
        int mkgauss;
        int mkn = mkn(i2);
        int i3 = 0;
        for (int i4 = 0; i4 < mkn; i4++) {
            while (true) {
                mkgauss = mkgauss(shake256, i2);
                if (mkgauss >= -127 && mkgauss <= 127) {
                    if (i4 == mkn - 1) {
                        if (((mkgauss & 1) ^ i3) != 0) {
                            break;
                        }
                    } else {
                        i3 ^= mkgauss & 1;
                        break;
                    }
                }
            }
            bArr[i + i4] = (byte) mkgauss;
        }
    }

    /* access modifiers changed from: package-private */
    public int poly_small_sqnorm(byte[] bArr, int i, int i2) {
        int mkn = mkn(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < mkn; i5++) {
            byte b = bArr[i + i5];
            i3 += b * b;
            i4 |= i3;
        }
        return (-(i4 >>> 31)) | i3;
    }

    /* access modifiers changed from: package-private */
    public void poly_small_to_fp(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, int i3) {
        int mkn = mkn(i3);
        for (int i4 = 0; i4 < mkn; i4++) {
            falconFPRArr[i + i4] = this.fpr.fpr_of((long) bArr[i2 + i4]);
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_sub_scaled(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10) {
        int mkn = mkn(i10);
        for (int i11 = 0; i11 < mkn; i11++) {
            int i12 = -iArr3[i7 + i11];
            int i13 = i + (i11 * i3);
            int i14 = i4;
            for (int i15 = 0; i15 < mkn; i15++) {
                zint_add_scaled_mul_small(iArr, i13, i2, iArr2, i14, i5, i12, i8, i9);
                if (i11 + i15 == mkn - 1) {
                    i12 = -i12;
                    i13 = i;
                } else {
                    i13 += i3;
                }
                i14 += i6;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void poly_sub_scaled_ntt(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10, int[] iArr4, int i11) {
        int i12 = i5;
        int mkn = mkn(i10);
        int i13 = i12 + 1;
        int mkn2 = i11 + mkn(i10);
        int mkn3 = mkn2 + mkn(i10);
        int i14 = mkn3 + (mkn * i13);
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i15 = 0;
        while (i15 < i13) {
            int i16 = falconSmallPrimeArr[i15].p;
            int modp_ninv31 = modp_ninv31(i16);
            int modp_R2 = modp_R2(i16, modp_ninv31);
            int modp_Rx = modp_Rx(i12, i16, modp_ninv31, modp_R2);
            int i17 = modp_R2;
            int i18 = modp_ninv31;
            int i19 = falconSmallPrimeArr[i15].g;
            int i20 = i16;
            int i21 = i15;
            modp_mkgm2(iArr4, i11, iArr4, mkn2, i10, i19, i16, i18);
            for (int i22 = 0; i22 < mkn; i22++) {
                iArr4[i14 + i22] = modp_set(iArr3[i7 + i22], i20);
            }
            int i23 = i20;
            modp_NTT2(iArr4, i14, iArr4, i11, i10, i23, i18);
            int i24 = mkn3 + i21;
            int i25 = i4;
            int i26 = i24;
            int i27 = 0;
            while (true) {
                if (i27 >= mkn) {
                    break;
                }
                iArr4[i26] = zint_mod_small_signed(iArr2, i25, i5, i23, i18, i17, modp_Rx);
                i27++;
                i25 += i6;
                i26 += i13;
            }
            int i28 = i23;
            modp_NTT2_ext(iArr4, i24, i13, iArr4, i11, i10, i23, i18);
            int i29 = i24;
            int i30 = 0;
            while (i30 < mkn) {
                int i31 = i18;
                iArr4[i29] = modp_montymul(modp_montymul(iArr4[i14 + i30], iArr4[i29], i28, i31), i17, i28, i31);
                i30++;
                i29 += i13;
            }
            modp_iNTT2_ext(iArr4, i24, i13, iArr4, mkn2, i10, i28, i18);
            i15 = i21 + 1;
            i12 = i5;
        }
        zint_rebuild_CRT(iArr4, mkn3, i13, i13, mkn, falconSmallPrimeArr, 1, iArr4, i14);
        int i32 = i;
        int i33 = 0;
        while (i33 < mkn) {
            zint_sub_scaled(iArr, i32, i2, iArr4, mkn3, i13, i8, i9);
            i33++;
            i32 += i3;
            mkn3 += i13;
        }
    }

    /* access modifiers changed from: package-private */
    public int solve_NTRU(int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, int i6, int[] iArr, int i7) {
        int i8;
        byte[] bArr5;
        int mkn = mkn(i);
        if (solve_NTRU_deepest(i, bArr3, i4, bArr4, i5, iArr, i7) == 0) {
            return 0;
        }
        int i9 = i;
        int i10 = i9;
        if (i9 <= 2) {
            while (true) {
                int i11 = i10 - 1;
                if (i10 <= 0) {
                    break;
                } else if (solve_NTRU_intermediate(i, bArr3, i4, bArr4, i5, i11, iArr, i7) == 0) {
                    return 0;
                } else {
                    i10 = i11;
                }
            }
        } else {
            while (true) {
                int i12 = i10 - 1;
                if (i10 > 2) {
                    if (solve_NTRU_intermediate(i, bArr3, i4, bArr4, i5, i12, iArr, i7) == 0) {
                        return 0;
                    }
                    i10 = i12;
                } else if (solve_NTRU_binary_depth1(i, bArr3, i4, bArr4, i5, iArr, i7) == 0 || solve_NTRU_binary_depth0(i, bArr3, i4, bArr4, i5, iArr, i7) == 0) {
                    return 0;
                }
            }
        }
        if (bArr2 == null) {
            bArr5 = new byte[mkn];
            i8 = 0;
        } else {
            bArr5 = bArr2;
            i8 = i3;
        }
        if (poly_big_to_small(bArr, i2, iArr, i7, i6, i) == 0) {
            return 0;
        }
        int i13 = i7 + mkn;
        if (poly_big_to_small(bArr5, i8, iArr, i13, i6, i) == 0) {
            return 0;
        }
        int i14 = i13 + mkn;
        int i15 = i14 + mkn;
        int i16 = i15 + mkn;
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i17 = falconSmallPrimeArr[0].p;
        int modp_ninv31 = modp_ninv31(i17);
        int i18 = i17;
        modp_mkgm2(iArr, i16, iArr, i7, i, falconSmallPrimeArr[0].g, i17, modp_ninv31);
        for (int i19 = 0; i19 < mkn; i19++) {
            iArr[i7 + i19] = modp_set(bArr5[i8 + i19], i18);
        }
        for (int i20 = 0; i20 < mkn; i20++) {
            iArr[i13 + i20] = modp_set(bArr3[i4 + i20], i18);
            iArr[i14 + i20] = modp_set(bArr4[i5 + i20], i18);
            iArr[i15 + i20] = modp_set(bArr[i2 + i20], i18);
        }
        int[] iArr2 = iArr;
        int[] iArr3 = iArr;
        int i21 = i16;
        int i22 = i;
        int i23 = i18;
        int i24 = modp_ninv31;
        modp_NTT2(iArr2, i13, iArr3, i21, i22, i23, i24);
        modp_NTT2(iArr2, i14, iArr3, i21, i22, i23, i24);
        modp_NTT2(iArr2, i15, iArr3, i21, i22, i23, i24);
        modp_NTT2(iArr2, i7, iArr3, i21, i22, i23, i24);
        int i25 = modp_ninv31;
        int modp_montymul = modp_montymul(12289, 1, i18, i25);
        for (int i26 = 0; i26 < mkn; i26++) {
            if (modp_sub(modp_montymul(iArr[i13 + i26], iArr[i7 + i26], i18, i25), modp_montymul(iArr[i14 + i26], iArr[i15 + i26], i18, i25), i18) != modp_montymul) {
                return 0;
            }
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int solve_NTRU_binary_depth0(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5 = i;
        int[] iArr2 = iArr;
        int i6 = i4;
        int i7 = 1 << i5;
        int i8 = i7 >> 1;
        int i9 = FalconSmallPrimeList.PRIMES[0].p;
        int modp_ninv31 = modp_ninv31(i9);
        int i10 = i6 + i8;
        int i11 = i10 + i8;
        int i12 = i11 + i7;
        int i13 = i12 + i7;
        int i14 = i13 + i7;
        int modp_R2 = modp_R2(i9, modp_ninv31);
        int i15 = modp_ninv31;
        int i16 = i11;
        int i17 = i9;
        modp_mkgm2(iArr, i13, iArr, i14, i, FalconSmallPrimeList.PRIMES[0].g, i9, i15);
        for (int i18 = 0; i18 < i8; i18++) {
            int i19 = i6 + i18;
            iArr2[i19] = modp_set(zint_one_to_plain(iArr2, i19), i17);
            int i20 = i10 + i18;
            iArr2[i20] = modp_set(zint_one_to_plain(iArr2, i20), i17);
        }
        int[] iArr3 = iArr;
        int[] iArr4 = iArr;
        int i21 = i13;
        int i22 = i5 - 1;
        int i23 = i17;
        int i24 = i15;
        modp_NTT2(iArr3, i4, iArr4, i21, i22, i23, i24);
        modp_NTT2(iArr3, i10, iArr4, i21, i22, i23, i24);
        for (int i25 = 0; i25 < i7; i25++) {
            iArr2[i16 + i25] = modp_set(bArr[i2 + i25], i17);
            iArr2[i12 + i25] = modp_set(bArr2[i3 + i25], i17);
        }
        int[] iArr5 = iArr;
        int[] iArr6 = iArr;
        int i26 = i13;
        int i27 = i;
        int i28 = i17;
        int i29 = i15;
        modp_NTT2(iArr5, i16, iArr6, i26, i27, i28, i29);
        modp_NTT2(iArr5, i12, iArr6, i26, i27, i28, i29);
        int i30 = 0;
        while (i30 < i7) {
            int i31 = i16 + i30;
            int i32 = iArr2[i31];
            int i33 = i31 + 1;
            int i34 = iArr2[i33];
            int i35 = i12 + i30;
            int i36 = iArr2[i35];
            int i37 = i35 + 1;
            int i38 = iArr2[i37];
            int i39 = i30 >> 1;
            int i40 = i8;
            int i41 = i15;
            int i42 = modp_R2;
            int modp_montymul = modp_montymul(iArr2[i6 + i39], i42, i17, i41);
            int i43 = i7;
            int modp_montymul2 = modp_montymul(iArr2[i10 + i39], i42, i17, i41);
            iArr2[i31] = modp_montymul(i38, modp_montymul, i17, i41);
            iArr2[i33] = modp_montymul(i36, modp_montymul, i17, i41);
            iArr2[i35] = modp_montymul(i34, modp_montymul2, i17, i41);
            iArr2[i37] = modp_montymul(i32, modp_montymul2, i17, i41);
            i30 += 2;
            i7 = i43;
            i8 = i40;
            i6 = i4;
            i15 = i41;
            int i44 = i;
        }
        int i45 = i8;
        int i46 = i15;
        int i47 = i7;
        int[] iArr7 = iArr;
        int[] iArr8 = iArr;
        int i48 = i14;
        int i49 = i;
        int i50 = i17;
        int i51 = i46;
        modp_iNTT2(iArr7, i16, iArr8, i48, i49, i50, i51);
        modp_iNTT2(iArr7, i12, iArr8, i48, i49, i50, i51);
        int i52 = modp_R2;
        int i53 = i4;
        int i54 = i53 + i47;
        int i55 = i54 + i47;
        System.arraycopy(iArr2, i16, iArr2, i53, i47 * 2);
        int i56 = i55 + i47;
        int i57 = i56 + i47;
        int i58 = i57 + i47;
        int i59 = i58 + i47;
        int[] iArr9 = iArr;
        modp_mkgm2(iArr9, i55, iArr8, i56, i49, FalconSmallPrimeList.PRIMES[0].g, i17, i46);
        int i60 = i55;
        int i61 = i17;
        int i62 = i46;
        modp_NTT2(iArr9, i4, iArr8, i60, i49, i61, i62);
        modp_NTT2(iArr9, i54, iArr8, i60, i49, i61, i62);
        int modp_set = modp_set(bArr[i2], i17);
        iArr2[i59] = modp_set;
        iArr2[i58] = modp_set;
        int i63 = i47;
        for (int i64 = 1; i64 < i63; i64++) {
            int i65 = i2 + i64;
            iArr2[i58 + i64] = modp_set(bArr[i65], i17);
            iArr2[(i59 + i63) - i64] = modp_set(-bArr[i65], i17);
        }
        int[] iArr10 = iArr;
        int[] iArr11 = iArr;
        int i66 = i55;
        int i67 = i;
        int i68 = i17;
        int i69 = i46;
        modp_NTT2(iArr10, i58, iArr11, i66, i67, i68, i69);
        modp_NTT2(iArr10, i59, iArr11, i66, i67, i68, i69);
        for (int i70 = 0; i70 < i63; i70++) {
            int modp_montymul3 = modp_montymul(iArr2[i59 + i70], i52, i17, i46);
            iArr2[i56 + i70] = modp_montymul(modp_montymul3, iArr2[i53 + i70], i17, i46);
            iArr2[i57 + i70] = modp_montymul(modp_montymul3, iArr2[i58 + i70], i17, i46);
        }
        int modp_set2 = modp_set(bArr2[i3], i17);
        iArr2[i59] = modp_set2;
        iArr2[i58] = modp_set2;
        for (int i71 = 1; i71 < i63; i71++) {
            int i72 = i3 + i71;
            iArr2[i58 + i71] = modp_set(bArr2[i72], i17);
            iArr2[(i59 + i63) - i71] = modp_set(-bArr2[i72], i17);
        }
        int[] iArr12 = iArr;
        int[] iArr13 = iArr;
        int i73 = i55;
        int i74 = i;
        int i75 = i17;
        int i76 = i46;
        modp_NTT2(iArr12, i58, iArr13, i73, i74, i75, i76);
        modp_NTT2(iArr12, i59, iArr13, i73, i74, i75, i76);
        for (int i77 = 0; i77 < i63; i77++) {
            int modp_montymul4 = modp_montymul(iArr2[i59 + i77], i52, i17, i46);
            int i78 = i56 + i77;
            iArr2[i78] = modp_add(iArr2[i78], modp_montymul(modp_montymul4, iArr2[i54 + i77], i17, i46), i17);
            int i79 = i57 + i77;
            iArr2[i79] = modp_add(iArr2[i79], modp_montymul(modp_montymul4, iArr2[i58 + i77], i17, i46), i17);
        }
        int[] iArr14 = iArr;
        int[] iArr15 = iArr;
        int i80 = i58;
        int i81 = i;
        int i82 = i54;
        int i83 = i63;
        modp_mkgm2(iArr14, i55, iArr15, i80, i81, FalconSmallPrimeList.PRIMES[0].g, i17, i46);
        int i84 = i17;
        int i85 = i46;
        modp_iNTT2(iArr14, i56, iArr15, i80, i81, i84, i85);
        modp_iNTT2(iArr14, i57, iArr15, i80, i81, i84, i85);
        for (int i86 = 0; i86 < i83; i86++) {
            int i87 = i56 + i86;
            iArr2[i55 + i86] = modp_norm(iArr2[i87], i17);
            iArr2[i87] = modp_norm(iArr2[i57 + i86], i17);
        }
        FalconFPR[] falconFPRArr = new FalconFPR[(i83 * 3)];
        int i88 = i83 + i83;
        for (int i89 = 0; i89 < i83; i89++) {
            falconFPRArr[i88 + i89] = this.fpr.fpr_of((long) iArr2[i56 + i89]);
        }
        int i90 = i46;
        int i91 = i;
        this.fft.FFT(falconFPRArr, i88, i91);
        int i92 = i45;
        System.arraycopy(falconFPRArr, i88, falconFPRArr, i83, i92);
        int i93 = i83 + i92;
        for (int i94 = 0; i94 < i83; i94++) {
            falconFPRArr[i93 + i94] = this.fpr.fpr_of((long) iArr2[i55 + i94]);
        }
        this.fft.FFT(falconFPRArr, i93, i91);
        this.fft.poly_div_autoadj_fft(falconFPRArr, i93, falconFPRArr, i83, i);
        this.fft.iFFT(falconFPRArr, i93, i91);
        for (int i95 = 0; i95 < i83; i95++) {
            iArr2[i55 + i95] = modp_set((int) this.fpr.fpr_rint(falconFPRArr[i93 + i95]), i17);
        }
        int i96 = i90;
        modp_mkgm2(iArr, i56, iArr, i57, i, FalconSmallPrimeList.PRIMES[0].g, i17, i96);
        for (int i97 = 0; i97 < i83; i97++) {
            iArr2[i58 + i97] = modp_set(bArr[i2 + i97], i17);
            iArr2[i59 + i97] = modp_set(bArr2[i3 + i97], i17);
        }
        int[] iArr16 = iArr;
        int[] iArr17 = iArr;
        int i98 = i56;
        int i99 = i;
        int i100 = i17;
        int i101 = i96;
        modp_NTT2(iArr16, i55, iArr17, i98, i99, i100, i101);
        modp_NTT2(iArr16, i58, iArr17, i98, i99, i100, i101);
        modp_NTT2(iArr16, i59, iArr17, i98, i99, i100, i101);
        for (int i102 = 0; i102 < i83; i102++) {
            int i103 = i96;
            int modp_montymul5 = modp_montymul(iArr2[i55 + i102], i52, i17, i103);
            int i104 = i53 + i102;
            iArr2[i104] = modp_sub(iArr2[i104], modp_montymul(modp_montymul5, iArr2[i58 + i102], i17, i103), i17);
            int i105 = i82 + i102;
            iArr2[i105] = modp_sub(iArr2[i105], modp_montymul(modp_montymul5, iArr2[i59 + i102], i17, i103), i17);
        }
        int[] iArr18 = iArr;
        int[] iArr19 = iArr;
        int i106 = i57;
        int i107 = i;
        int i108 = i17;
        int i109 = i96;
        modp_iNTT2(iArr18, i4, iArr19, i106, i107, i108, i109);
        modp_iNTT2(iArr18, i82, iArr19, i106, i107, i108, i109);
        for (int i110 = 0; i110 < i83; i110++) {
            int i111 = i53 + i110;
            iArr2[i111] = modp_norm(iArr2[i111], i17);
            int i112 = i82 + i110;
            iArr2[i112] = modp_norm(iArr2[i112], i17);
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int solve_NTRU_binary_depth1(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5;
        int i6;
        FalconKeyGen falconKeyGen = this;
        int[] iArr2 = iArr;
        int i7 = i4;
        int i8 = 1 << i;
        int i9 = i - 1;
        int i10 = 1 << i9;
        int i11 = i10 >> 1;
        int[] iArr3 = falconKeyGen.MAX_BL_SMALL;
        int i12 = iArr3[1];
        int i13 = iArr3[2];
        int i14 = falconKeyGen.MAX_BL_LARGE[1];
        int i15 = i13 * i11;
        int i16 = i7 + i15;
        int i17 = i16 + i15;
        int i18 = i14 * i10;
        int i19 = i17 + i18;
        int i20 = 0;
        while (i20 < i14) {
            int i21 = FalconSmallPrimeList.PRIMES[i20].p;
            int modp_ninv31 = falconKeyGen.modp_ninv31(i21);
            int i22 = i9;
            int modp_R2 = falconKeyGen.modp_R2(i21, modp_ninv31);
            int modp_Rx = falconKeyGen.modp_Rx(i13, i21, modp_ninv31, modp_R2);
            int i23 = i17 + i20;
            int i24 = i19 + i20;
            int i25 = i8;
            int i26 = i7;
            int i27 = i16;
            int i28 = 0;
            while (true) {
                i6 = i20;
                if (i28 >= i11) {
                    break;
                }
                int i29 = i21;
                int[] iArr4 = iArr;
                int i30 = i11;
                int i31 = i18;
                int i32 = i13;
                int i33 = i10;
                int i34 = i17;
                int i35 = i29;
                int i36 = i14;
                int i37 = modp_ninv31;
                int i38 = i13;
                int i39 = modp_R2;
                int i40 = i12;
                int i41 = modp_Rx;
                iArr2[i23] = zint_mod_small_signed(iArr4, i26, i32, i35, i37, i39, i41);
                iArr2[i24] = zint_mod_small_signed(iArr4, i27, i38, i35, i37, i39, i41);
                i28++;
                i26 += i38;
                i27 += i38;
                i23 += i36;
                i24 += i36;
                i14 = i36;
                i18 = i31;
                i17 = i34;
                i21 = i29;
                i20 = i6;
                i11 = i30;
                i10 = i33;
                i19 = i19;
                i13 = i38;
                i12 = i40;
            }
            int i42 = i19;
            int i43 = i18;
            int i44 = i13;
            int i45 = i12;
            int i46 = i11;
            int i47 = i10;
            int i48 = i17;
            int i49 = i14;
            i20 = i6 + 1;
            i9 = i22;
            i8 = i25;
            i11 = i46;
            i10 = i47;
            falconKeyGen = this;
        }
        int i50 = i18;
        int i51 = i11;
        int i52 = i8;
        int i53 = i10;
        int i54 = i9;
        int i55 = i14;
        System.arraycopy(iArr2, i17, iArr2, i7, i50);
        int i56 = i7 + i50;
        System.arraycopy(iArr2, i19, iArr2, i56, i50);
        int i57 = i50 + i56;
        int i58 = i12;
        int i59 = i58 * i53;
        int i60 = i57 + i59;
        int i61 = i60 + i59;
        int i62 = 0;
        while (i62 < i55) {
            int i63 = FalconSmallPrimeList.PRIMES[i62].p;
            int modp_ninv312 = modp_ninv31(i63);
            int i64 = i61 + i52;
            int i65 = i64 + i53;
            int i66 = i59;
            int i67 = i65 + i52;
            int i68 = i60;
            int i69 = i65;
            int i70 = i64;
            int modp_R22 = modp_R2(i63, modp_ninv312);
            int i71 = modp_ninv312;
            int i72 = i57;
            int i73 = i63;
            int i74 = i58;
            int i75 = i62;
            int i76 = i56;
            int i77 = i51;
            int i78 = i55;
            modp_mkgm2(iArr, i61, iArr, i70, i, FalconSmallPrimeList.PRIMES[i62].g, i73, i71);
            int i79 = i52;
            for (int i80 = 0; i80 < i79; i80++) {
                int i81 = i73;
                iArr2[i69 + i80] = modp_set(bArr[i2 + i80], i81);
                iArr2[i67 + i80] = modp_set(bArr2[i3 + i80], i81);
            }
            int[] iArr5 = iArr;
            int[] iArr6 = iArr;
            int i82 = i61;
            int i83 = i;
            int i84 = i71;
            modp_NTT2(iArr5, i69, iArr6, i82, i83, i73, i84);
            modp_NTT2(iArr5, i67, iArr6, i82, i83, i73, i84);
            int i85 = i;
            int i86 = i54;
            while (i85 > i86) {
                int[] iArr7 = iArr;
                int i87 = i85;
                int i88 = i73;
                int i89 = i71;
                int i90 = i85;
                int i91 = modp_R22;
                modp_poly_rec_res(iArr7, i69, i87, i88, i89, i91);
                modp_poly_rec_res(iArr7, i67, i90, i88, i89, i91);
                i85 = i90 - 1;
            }
            int i92 = i61 + i53;
            int i93 = i53;
            System.arraycopy(iArr2, i70, iArr2, i92, i93);
            int i94 = i92 + i93;
            System.arraycopy(iArr2, i69, iArr2, i94, i93);
            int i95 = i94 + i93;
            System.arraycopy(iArr2, i67, iArr2, i95, i93);
            int i96 = i95 + i93;
            int i97 = i96 + i77;
            int i98 = i7 + i75;
            int i99 = i76 + i75;
            int i100 = i98;
            int i101 = i99;
            int i102 = 0;
            while (i102 < i77) {
                iArr2[i96 + i102] = iArr2[i100];
                iArr2[i97 + i102] = iArr2[i101];
                i102++;
                i100 += i78;
                i101 += i78;
            }
            int[] iArr8 = iArr;
            int[] iArr9 = iArr;
            int i103 = i94;
            int i104 = i61;
            int i105 = i93;
            int i106 = i - 2;
            int i107 = i92;
            int i108 = i73;
            int i109 = i86;
            int i110 = i71;
            modp_NTT2(iArr8, i96, iArr9, i104, i106, i108, i110);
            modp_NTT2(iArr8, i97, iArr9, i104, i106, i108, i110);
            int i111 = i98;
            int i112 = i99;
            int i113 = 0;
            while (i113 < i77) {
                int i114 = i113 << 1;
                int i115 = i103 + i114;
                int i116 = iArr2[i115];
                int i117 = iArr2[i115 + 1];
                int i118 = i114 + i95;
                int i119 = iArr2[i118];
                int i120 = iArr2[i118 + 1];
                int i121 = i96;
                int i122 = i73;
                int i123 = i71;
                int i124 = i79;
                int i125 = modp_R22;
                int i126 = i77;
                int i127 = i123;
                int modp_montymul = modp_montymul(iArr2[i96 + i113], i125, i122, i127);
                int i128 = i105;
                int modp_montymul2 = modp_montymul(iArr2[i97 + i113], i125, i122, i127);
                iArr2[i111] = modp_montymul(i120, modp_montymul, i122, i127);
                iArr2[i111 + i78] = modp_montymul(i119, modp_montymul, i122, i127);
                iArr2[i112] = modp_montymul(i117, modp_montymul2, i122, i127);
                iArr2[i112 + i78] = modp_montymul(i116, modp_montymul2, i122, i127);
                i113++;
                int i129 = i78 << 1;
                i111 += i129;
                i112 += i129;
                i73 = i122;
                i96 = i121;
                i105 = i128;
                int i130 = i126;
                modp_R22 = i125;
                i79 = i124;
                i71 = i127;
                i77 = i130;
            }
            int i131 = i105;
            int i132 = i77;
            int i133 = i71;
            int i134 = i73;
            int[] iArr10 = iArr;
            int i135 = i78;
            int[] iArr11 = iArr;
            int i136 = i107;
            int i137 = i109;
            int i138 = i134;
            int i139 = i79;
            int i140 = i133;
            modp_iNTT2_ext(iArr10, i98, i135, iArr11, i136, i137, i138, i140);
            modp_iNTT2_ext(iArr10, i99, i135, iArr11, i136, i137, i138, i140);
            int i141 = i74;
            if (i75 < i141) {
                int[] iArr12 = iArr;
                int[] iArr13 = iArr;
                int i142 = i107;
                int i143 = i109;
                int i144 = i134;
                int i145 = i133;
                modp_iNTT2(iArr12, i103, iArr13, i142, i143, i144, i145);
                modp_iNTT2(iArr12, i95, iArr13, i142, i143, i144, i145);
                int i146 = i72 + i75;
                int i147 = i68 + i75;
                i5 = i131;
                int i148 = 0;
                while (i148 < i5) {
                    iArr2[i146] = iArr2[i103 + i148];
                    iArr2[i147] = iArr2[i95 + i148];
                    i148++;
                    i146 += i141;
                    i147 += i141;
                }
            } else {
                i5 = i131;
            }
            i62 = i75 + 1;
            i58 = i141;
            i52 = i139;
            i53 = i5;
            i59 = i66;
            i56 = i76;
            i60 = i68;
            i57 = i72;
            i55 = i78;
            i54 = i109;
            i7 = i4;
            i51 = i132;
        }
        int i149 = i57;
        int i150 = i56;
        int i151 = i54;
        int i152 = i51;
        int i153 = i53;
        int i154 = i55;
        int i155 = i58;
        int[] iArr14 = iArr;
        int i156 = i153 << 1;
        int[] iArr15 = iArr;
        int i157 = i61;
        zint_rebuild_CRT(iArr14, i4, i154, i154, i156, FalconSmallPrimeList.PRIMES, 1, iArr15, i157);
        zint_rebuild_CRT(iArr14, i149, i58, i58, i156, FalconSmallPrimeList.PRIMES, 1, iArr15, i157);
        FalconFPR[] falconFPRArr = new FalconFPR[i153];
        FalconFPR[] falconFPRArr2 = new FalconFPR[i153];
        int[] iArr16 = iArr;
        int i158 = i154;
        int i159 = i154;
        int i160 = i151;
        poly_big_to_fp(falconFPRArr, 0, iArr16, i4, i158, i159, i160);
        poly_big_to_fp(falconFPRArr2, 0, iArr16, i150, i158, i159, i160);
        int i161 = i4;
        System.arraycopy(iArr2, i149, iArr2, i161, i58 * 2 * i153);
        FalconFPR[] falconFPRArr3 = new FalconFPR[i153];
        int i162 = i58;
        FalconFPR[] falconFPRArr4 = new FalconFPR[i153];
        int i163 = i58;
        FalconFPR[] falconFPRArr5 = falconFPRArr3;
        int i164 = i151;
        poly_big_to_fp(falconFPRArr3, 0, iArr16, i4, i162, i163, i164);
        poly_big_to_fp(falconFPRArr4, 0, iArr16, i161 + i59, i162, i163, i164);
        int i165 = i151;
        this.fft.FFT(falconFPRArr, 0, i165);
        this.fft.FFT(falconFPRArr2, 0, i165);
        this.fft.FFT(falconFPRArr5, 0, i165);
        FalconFPR[] falconFPRArr6 = falconFPRArr4;
        this.fft.FFT(falconFPRArr6, 0, i165);
        FalconFPR[] falconFPRArr7 = new FalconFPR[i153];
        FalconFPR[] falconFPRArr8 = new FalconFPR[i152];
        int i166 = i165;
        this.fft.poly_add_muladj_fft(falconFPRArr7, 0, falconFPRArr, 0, falconFPRArr2, 0, falconFPRArr5, 0, falconFPRArr6, 0, i166);
        int i167 = i166;
        FalconFPR[] falconFPRArr9 = falconFPRArr7;
        FalconFPR[] falconFPRArr10 = falconFPRArr6;
        this.fft.poly_invnorm2_fft(falconFPRArr8, 0, falconFPRArr5, 0, falconFPRArr6, 0, i167);
        this.fft.poly_mul_autoadj_fft(falconFPRArr9, 0, falconFPRArr8, 0, i167);
        int i168 = i167;
        this.fft.iFFT(falconFPRArr9, 0, i168);
        int i169 = 0;
        while (i169 < i153) {
            FalconFPR falconFPR = falconFPRArr9[i169];
            FPREngine fPREngine = this.fpr;
            if (fPREngine.fpr_lt(falconFPR, fPREngine.fpr_ptwo63m1)) {
                FPREngine fPREngine2 = this.fpr;
                if (fPREngine2.fpr_lt(fPREngine2.fpr_mtwo63m1, falconFPR)) {
                    FPREngine fPREngine3 = this.fpr;
                    falconFPRArr9[i169] = fPREngine3.fpr_of(fPREngine3.fpr_rint(falconFPR));
                    i169++;
                }
            }
            return 0;
        }
        this.fft.FFT(falconFPRArr9, 0, i168);
        FalconFPR[] falconFPRArr11 = falconFPRArr9;
        int i170 = i168;
        this.fft.poly_mul_fft(falconFPRArr5, 0, falconFPRArr11, 0, i170);
        this.fft.poly_mul_fft(falconFPRArr10, 0, falconFPRArr11, 0, i170);
        this.fft.poly_sub(falconFPRArr, 0, falconFPRArr5, 0, i170);
        this.fft.poly_sub(falconFPRArr2, 0, falconFPRArr10, 0, i170);
        this.fft.iFFT(falconFPRArr, 0, i168);
        this.fft.iFFT(falconFPRArr2, 0, i168);
        int i171 = i161 + i153;
        for (int i172 = 0; i172 < i153; i172++) {
            iArr[i161 + i172] = (int) this.fpr.fpr_rint(falconFPRArr[i172]);
            iArr[i171 + i172] = (int) this.fpr.fpr_rint(falconFPRArr2[i172]);
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int solve_NTRU_deepest(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int[] iArr2 = iArr;
        int i5 = i4;
        int i6 = this.MAX_BL_SMALL[i];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i7 = i5 + i6;
        int i8 = i7 + i6;
        int i9 = i8 + i6;
        int i10 = i9 + i6;
        int[] iArr3 = iArr;
        int i11 = i8;
        make_fg(iArr3, i11, bArr, i2, bArr2, i3, i, i, 0);
        zint_rebuild_CRT(iArr3, i11, i6, i6, 2, falconSmallPrimeArr, 0, iArr, i10);
        return (zint_bezout(iArr3, i7, iArr, i4, iArr, i8, iArr, i9, i6, iArr, i10) != 0 && zint_mul_small(iArr2, i5, i6, 12289) == 0 && zint_mul_small(iArr2, i7, i6, 12289) == 0) ? 1 : 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x04e2  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x048d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int solve_NTRU_intermediate(int r40, byte[] r41, int r42, byte[] r43, int r44, int r45, int[] r46, int r47) {
        /*
            r39 = this;
            r15 = r39
            r14 = r45
            r13 = r46
            r12 = r47
            int r11 = r40 - r14
            r16 = 1
            int r10 = r16 << r11
            int r9 = r10 >> 1
            int[] r0 = r15.MAX_BL_SMALL
            r8 = r0[r14]
            int r1 = r14 + 1
            r7 = r0[r1]
            int[] r0 = r15.MAX_BL_LARGE
            r6 = r0[r14]
            org.bouncycastle.pqc.crypto.falcon.FalconSmallPrime[] r17 = org.bouncycastle.pqc.crypto.falcon.FalconSmallPrimeList.PRIMES
            int r18 = r7 * r9
            int r0 = r12 + r18
            int r5 = r0 + r18
            r19 = 1
            r0 = r39
            r1 = r46
            r2 = r5
            r3 = r41
            r4 = r42
            r20 = r5
            r5 = r43
            r21 = r6
            r6 = r44
            r22 = r7
            r7 = r40
            r14 = r8
            r8 = r45
            r23 = r11
            r11 = r9
            r9 = r19
            r0.make_fg(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r9 = r21
            int r6 = r10 * r9
            int r19 = r12 + r6
            int r8 = r19 + r6
            int r0 = r10 * 2
            int r0 = r0 * r14
            r1 = r20
            java.lang.System.arraycopy(r13, r1, r13, r8, r0)
            int r0 = r14 * r10
            int r20 = r8 + r0
            int r7 = r20 + r0
            int r0 = r11 * 2
            r6 = r22
            int r0 = r0 * r6
            java.lang.System.arraycopy(r13, r12, r13, r7, r0)
            int r18 = r7 + r18
            r5 = 0
            r4 = 0
        L_0x006a:
            if (r4 >= r9) goto L_0x00dc
            r0 = r17[r4]
            int r3 = r0.p
            int r2 = r15.modp_ninv31(r3)
            int r1 = r15.modp_R2(r3, r2)
            int r21 = r15.modp_Rx(r6, r3, r2, r1)
            int r0 = r12 + r4
            int r22 = r19 + r4
            r25 = r7
            r26 = r18
            r24 = r22
            r22 = r0
            r0 = 0
        L_0x0089:
            if (r0 >= r11) goto L_0x00d2
            r27 = r0
            r0 = r39
            r28 = r1
            r1 = r46
            r29 = r2
            r2 = r25
            r30 = r3
            r3 = r6
            r31 = r4
            r4 = r30
            r5 = r29
            r33 = r6
            r6 = r28
            r34 = r7
            r7 = r21
            int r0 = r0.zint_mod_small_signed(r1, r2, r3, r4, r5, r6, r7)
            r13[r22] = r0
            r0 = r39
            r2 = r26
            r3 = r33
            int r0 = r0.zint_mod_small_signed(r1, r2, r3, r4, r5, r6, r7)
            r13[r24] = r0
            int r0 = r27 + 1
            int r25 = r25 + r33
            int r26 = r26 + r33
            int r22 = r22 + r9
            int r24 = r24 + r9
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r33
            r7 = r34
            r5 = 0
            goto L_0x0089
        L_0x00d2:
            r31 = r4
            r33 = r6
            r34 = r7
            int r4 = r31 + 1
            r5 = 0
            goto L_0x006a
        L_0x00dc:
            r34 = r7
            r7 = 0
        L_0x00df:
            if (r7 >= r9) goto L_0x0296
            r0 = r17[r7]
            int r6 = r0.p
            int r5 = r15.modp_ninv31(r6)
            int r4 = r15.modp_R2(r6, r5)
            if (r7 != r14) goto L_0x0116
            r18 = 1
            r0 = r39
            r1 = r46
            r2 = r8
            r3 = r14
            r35 = r4
            r4 = r14
            r40 = r5
            r5 = r10
            r41 = r6
            r6 = r17
            r12 = r7
            r7 = r18
            r18 = r8
            r8 = r46
            r21 = r9
            r9 = r34
            r0.zint_rebuild_CRT(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r7 = 1
            r2 = r20
            r0.zint_rebuild_CRT(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0121
        L_0x0116:
            r35 = r4
            r40 = r5
            r41 = r6
            r12 = r7
            r18 = r8
            r21 = r9
        L_0x0121:
            int r9 = r34 + r10
            int r22 = r9 + r10
            int r24 = r22 + r10
            r0 = r17[r12]
            int r6 = r0.g
            r0 = r39
            r1 = r46
            r2 = r34
            r3 = r46
            r4 = r9
            r5 = r23
            r7 = r41
            r8 = r40
            r0.modp_mkgm2(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r12 >= r14) goto L_0x0174
            int r2 = r18 + r12
            int r25 = r20 + r12
            r0 = r2
            r1 = r25
            r5 = 0
        L_0x0147:
            if (r5 >= r10) goto L_0x015a
            int r3 = r22 + r5
            r4 = r13[r0]
            r13[r3] = r4
            int r3 = r24 + r5
            r4 = r13[r1]
            r13[r3] = r4
            int r5 = r5 + 1
            int r0 = r0 + r14
            int r1 = r1 + r14
            goto L_0x0147
        L_0x015a:
            r0 = r39
            r1 = r46
            r3 = r14
            r4 = r46
            r5 = r9
            r6 = r23
            r7 = r41
            r8 = r40
            r0.modp_iNTT2_ext(r1, r2, r3, r4, r5, r6, r7, r8)
            r2 = r25
            r0.modp_iNTT2_ext(r1, r2, r3, r4, r5, r6, r7, r8)
            r8 = r41
            goto L_0x01ce
        L_0x0174:
            r7 = r40
            r8 = r41
            r6 = r35
            int r25 = r15.modp_Rx(r14, r8, r7, r6)
            r26 = r18
            r27 = r20
            r5 = 0
        L_0x0183:
            if (r5 >= r10) goto L_0x01b5
            int r28 = r22 + r5
            r0 = r39
            r1 = r46
            r2 = r26
            r3 = r14
            r4 = r8
            r29 = r5
            r5 = r7
            r35 = r6
            r40 = r7
            r7 = r25
            int r0 = r0.zint_mod_small_signed(r1, r2, r3, r4, r5, r6, r7)
            r13[r28] = r0
            int r28 = r24 + r29
            r0 = r39
            r2 = r27
            r5 = r40
            int r0 = r0.zint_mod_small_signed(r1, r2, r3, r4, r5, r6, r7)
            r13[r28] = r0
            int r5 = r29 + 1
            int r26 = r26 + r14
            int r27 = r27 + r14
            r7 = r40
            goto L_0x0183
        L_0x01b5:
            r35 = r6
            r40 = r7
            r0 = r39
            r1 = r46
            r2 = r22
            r3 = r46
            r4 = r34
            r5 = r23
            r6 = r8
            r0.modp_NTT2(r1, r2, r3, r4, r5, r6, r7)
            r2 = r24
            r0.modp_NTT2(r1, r2, r3, r4, r5, r6, r7)
        L_0x01ce:
            int r25 = r24 + r10
            int r26 = r25 + r11
            int r27 = r47 + r12
            int r28 = r19 + r12
            r0 = r27
            r1 = r28
            r5 = 0
        L_0x01db:
            if (r5 >= r11) goto L_0x01f0
            int r2 = r25 + r5
            r3 = r13[r0]
            r13[r2] = r3
            int r2 = r26 + r5
            r3 = r13[r1]
            r13[r2] = r3
            int r5 = r5 + 1
            int r0 = r0 + r21
            int r1 = r1 + r21
            goto L_0x01db
        L_0x01f0:
            int r29 = r23 + -1
            r0 = r39
            r1 = r46
            r2 = r25
            r3 = r46
            r4 = r34
            r5 = r29
            r6 = r8
            r7 = r40
            r0.modp_NTT2(r1, r2, r3, r4, r5, r6, r7)
            r2 = r26
            r0.modp_NTT2(r1, r2, r3, r4, r5, r6, r7)
            r0 = r27
            r1 = r28
            r5 = 0
        L_0x020e:
            if (r5 >= r11) goto L_0x0267
            int r2 = r5 << 1
            int r3 = r22 + r2
            r4 = r13[r3]
            int r3 = r3 + 1
            r3 = r13[r3]
            int r2 = r24 + r2
            r6 = r13[r2]
            int r2 = r2 + 1
            r2 = r13[r2]
            int r7 = r25 + r5
            r7 = r13[r7]
            r41 = r14
            r14 = r40
            r40 = r11
            r11 = r35
            int r7 = r15.modp_montymul(r7, r11, r8, r14)
            int r29 = r26 + r5
            r30 = r10
            r10 = r13[r29]
            int r10 = r15.modp_montymul(r10, r11, r8, r14)
            int r2 = r15.modp_montymul(r2, r7, r8, r14)
            r13[r0] = r2
            int r2 = r0 + r21
            int r6 = r15.modp_montymul(r6, r7, r8, r14)
            r13[r2] = r6
            int r2 = r15.modp_montymul(r3, r10, r8, r14)
            r13[r1] = r2
            int r6 = r1 + r21
            int r2 = r15.modp_montymul(r4, r10, r8, r14)
            r13[r6] = r2
            int r5 = r5 + 1
            int r2 = r21 << 1
            int r0 = r0 + r2
            int r1 = r1 + r2
            r10 = r30
            r11 = r40
            r40 = r14
            r14 = r41
            goto L_0x020e
        L_0x0267:
            r30 = r10
            r41 = r14
            r14 = r40
            r40 = r11
            r0 = r39
            r1 = r46
            r2 = r27
            r3 = r21
            r4 = r46
            r5 = r9
            r6 = r23
            r7 = r8
            r10 = r8
            r8 = r14
            r0.modp_iNTT2_ext(r1, r2, r3, r4, r5, r6, r7, r8)
            r2 = r28
            r7 = r10
            r0.modp_iNTT2_ext(r1, r2, r3, r4, r5, r6, r7, r8)
            int r7 = r12 + 1
            r14 = r41
            r12 = r47
            r8 = r18
            r9 = r21
            r10 = r30
            goto L_0x00df
        L_0x0296:
            r18 = r8
            r21 = r9
            r30 = r10
            r40 = r11
            r41 = r14
            r7 = 1
            r0 = r39
            r1 = r46
            r2 = r47
            r3 = r21
            r4 = r21
            r5 = r30
            r6 = r17
            r8 = r46
            r9 = r34
            r0.zint_rebuild_CRT(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r2 = r19
            r0.zint_rebuild_CRT(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            org.bouncycastle.pqc.crypto.falcon.FalconFPR[] r14 = new org.bouncycastle.pqc.crypto.falcon.FalconFPR[r10]
            org.bouncycastle.pqc.crypto.falcon.FalconFPR[] r12 = new org.bouncycastle.pqc.crypto.falcon.FalconFPR[r10]
            org.bouncycastle.pqc.crypto.falcon.FalconFPR[] r11 = new org.bouncycastle.pqc.crypto.falcon.FalconFPR[r10]
            org.bouncycastle.pqc.crypto.falcon.FalconFPR[] r9 = new org.bouncycastle.pqc.crypto.falcon.FalconFPR[r10]
            r0 = r40
            org.bouncycastle.pqc.crypto.falcon.FalconFPR[] r8 = new org.bouncycastle.pqc.crypto.falcon.FalconFPR[r0]
            int[] r7 = new int[r10]
            r6 = 10
            r5 = r41
            if (r5 <= r6) goto L_0x02d2
            r17 = 10
            goto L_0x02d4
        L_0x02d2:
            r17 = r5
        L_0x02d4:
            int r0 = r18 + r5
            int r4 = r0 - r17
            r2 = 0
            r0 = r39
            r1 = r11
            r3 = r46
            r22 = r5
            r5 = r17
            r6 = r22
            r24 = r7
            r7 = r23
            r0.poly_big_to_fp(r1, r2, r3, r4, r5, r6, r7)
            int r0 = r20 + r22
            int r4 = r0 - r17
            r0 = r39
            r1 = r9
            r0.poly_big_to_fp(r1, r2, r3, r4, r5, r6, r7)
            int r0 = r22 - r17
            int r17 = r0 * 31
            int[] r0 = r15.bitlength_avg
            r7 = r45
            r0 = r0[r7]
            int[] r1 = r15.bitlength_std
            r1 = r1[r7]
            int r2 = r1 * 6
            int r22 = r0 - r2
            int r1 = r1 * 6
            int r25 = r0 + r1
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r5 = r23
            r4 = 0
            r0.FFT(r11, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r0.FFT(r9, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r23 = 0
            r26 = 0
            r2 = 0
            r1 = r8
            r3 = r11
            r4 = r23
            r23 = r5
            r5 = r9
            r41 = r6
            r6 = r26
            r13 = r7
            r7 = r23
            r0.poly_invnorm2_fft(r1, r2, r3, r4, r5, r6, r7)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r6 = 0
            r0.poly_adj_fft(r11, r6, r7)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r0.poly_adj_fft(r9, r6, r7)
            int r0 = r21 * 31
            int r1 = r0 - r22
            r5 = r0
            r22 = r1
            r4 = r21
            r3 = 10
        L_0x0346:
            if (r4 <= r3) goto L_0x034b
            r23 = 10
            goto L_0x034d
        L_0x034b:
            r23 = r4
        L_0x034d:
            int r0 = r4 - r23
            int r26 = r0 * 31
            int r0 = r47 + r4
            int r27 = r0 - r23
            r2 = 0
            r0 = r39
            r1 = r14
            r28 = 10
            r3 = r46
            r29 = r4
            r4 = r27
            r37 = r5
            r5 = r23
            r6 = r21
            r40 = r7
            r0.poly_big_to_fp(r1, r2, r3, r4, r5, r6, r7)
            int r4 = r19 + r29
            int r4 = r4 - r23
            r1 = r12
            r0.poly_big_to_fp(r1, r2, r3, r4, r5, r6, r7)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r6 = 0
            r0.FFT(r14, r6, r7)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r0.FFT(r12, r6, r7)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r4 = 0
            r1 = r14
            r3 = r11
            r5 = r7
            r0.poly_mul_fft(r1, r2, r3, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r1 = r12
            r3 = r9
            r0.poly_mul_fft(r1, r2, r3, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r3 = r14
            r0.poly_add(r1, r2, r3, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r3 = r8
            r0.poly_mul_autoadj_fft(r1, r2, r3, r4, r5)
            org.bouncycastle.pqc.crypto.falcon.FalconFFT r0 = r15.fft
            r0.iFFT(r12, r6, r7)
            int r0 = r22 - r26
            int r0 = r0 + r17
            if (r0 >= 0) goto L_0x03ac
            int r0 = -r0
            org.bouncycastle.pqc.crypto.falcon.FPREngine r1 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r1 = r1.fpr_two
            goto L_0x03b0
        L_0x03ac:
            org.bouncycastle.pqc.crypto.falcon.FPREngine r1 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r1 = r1.fpr_onehalf
        L_0x03b0:
            org.bouncycastle.pqc.crypto.falcon.FPREngine r2 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r2 = r2.fpr_one
        L_0x03b4:
            if (r0 == 0) goto L_0x03c9
            r3 = r0 & 1
            if (r3 == 0) goto L_0x03c0
            org.bouncycastle.pqc.crypto.falcon.FPREngine r3 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r2 = r3.fpr_mul(r2, r1)
        L_0x03c0:
            int r0 = r0 >> 1
            org.bouncycastle.pqc.crypto.falcon.FPREngine r3 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r1 = r3.fpr_sqr(r1)
            goto L_0x03b4
        L_0x03c9:
            r5 = 0
        L_0x03ca:
            if (r5 >= r10) goto L_0x03f6
            org.bouncycastle.pqc.crypto.falcon.FPREngine r0 = r15.fpr
            r1 = r12[r5]
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r0 = r0.fpr_mul(r1, r2)
            org.bouncycastle.pqc.crypto.falcon.FPREngine r1 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r3 = r1.fpr_mtwo31m1
            boolean r1 = r1.fpr_lt(r3, r0)
            if (r1 == 0) goto L_0x03f5
            org.bouncycastle.pqc.crypto.falcon.FPREngine r1 = r15.fpr
            org.bouncycastle.pqc.crypto.falcon.FalconFPR r3 = r1.fpr_ptwo31m1
            boolean r1 = r1.fpr_lt(r0, r3)
            if (r1 != 0) goto L_0x03e9
            goto L_0x03f5
        L_0x03e9:
            org.bouncycastle.pqc.crypto.falcon.FPREngine r1 = r15.fpr
            long r0 = r1.fpr_rint(r0)
            int r1 = (int) r0
            r24[r5] = r1
            int r5 = r5 + 1
            goto L_0x03ca
        L_0x03f5:
            return r6
        L_0x03f6:
            int r23 = r22 / 31
            int r26 = r22 % 31
            r0 = 4
            if (r13 > r0) goto L_0x043d
            r27 = 0
            r0 = r39
            r1 = r46
            r2 = r47
            r3 = r29
            r4 = r21
            r5 = r46
            r30 = 0
            r6 = r18
            r31 = r7
            r7 = r41
            r32 = r8
            r8 = r41
            r33 = r9
            r9 = r24
            r38 = r10
            r10 = r27
            r27 = r31
            r31 = r11
            r11 = r23
            r35 = r12
            r12 = r26
            r13 = r27
            r36 = r14
            r14 = r46
            r15 = r34
            r0.poly_sub_scaled_ntt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r10 = 0
            r2 = r19
            r6 = r20
            r0.poly_sub_scaled_ntt(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            goto L_0x0472
        L_0x043d:
            r27 = r7
            r32 = r8
            r33 = r9
            r38 = r10
            r31 = r11
            r35 = r12
            r36 = r14
            r30 = 0
            r10 = 0
            r0 = r39
            r1 = r46
            r2 = r47
            r3 = r29
            r4 = r21
            r5 = r46
            r6 = r18
            r7 = r41
            r8 = r41
            r9 = r24
            r11 = r23
            r12 = r26
            r13 = r27
            r0.poly_sub_scaled(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r2 = r19
            r6 = r20
            r0.poly_sub_scaled(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
        L_0x0472:
            int r0 = r22 + r25
            int r1 = r0 + 10
            r2 = r37
            if (r1 >= r2) goto L_0x0486
            int r4 = r29 * 31
            int r0 = r0 + 41
            if (r4 < r0) goto L_0x0484
            int r4 = r29 + -1
            r5 = r1
            goto L_0x0489
        L_0x0484:
            r5 = r1
            goto L_0x0487
        L_0x0486:
            r5 = r2
        L_0x0487:
            r4 = r29
        L_0x0489:
            r0 = r41
            if (r22 > 0) goto L_0x04e2
            if (r4 >= r0) goto L_0x04cc
            r12 = r47
            r1 = r38
            r5 = 0
        L_0x0494:
            if (r5 >= r1) goto L_0x04c9
            int r2 = r12 + r4
            int r2 = r2 + -1
            r3 = r46
            r2 = r3[r2]
            int r2 = r2 >>> 30
            int r2 = -r2
            int r2 = r2 >>> 1
            r6 = r4
        L_0x04a4:
            if (r6 >= r0) goto L_0x04ad
            int r7 = r12 + r6
            r3[r7] = r2
            int r6 = r6 + 1
            goto L_0x04a4
        L_0x04ad:
            int r2 = r19 + r4
            int r2 = r2 + -1
            r2 = r3[r2]
            int r2 = r2 >>> 30
            int r2 = -r2
            int r2 = r2 >>> 1
            r6 = r4
        L_0x04b9:
            if (r6 >= r0) goto L_0x04c2
            int r7 = r19 + r6
            r3[r7] = r2
            int r6 = r6 + 1
            goto L_0x04b9
        L_0x04c2:
            int r5 = r5 + 1
            int r12 = r12 + r21
            int r19 = r19 + r21
            goto L_0x0494
        L_0x04c9:
            r3 = r46
            goto L_0x04d0
        L_0x04cc:
            r3 = r46
            r1 = r38
        L_0x04d0:
            r2 = r47
            r4 = r2
            r5 = 0
        L_0x04d4:
            int r6 = r1 << 1
            if (r5 >= r6) goto L_0x04e1
            java.lang.System.arraycopy(r3, r2, r3, r4, r0)
            int r5 = r5 + 1
            int r4 = r4 + r0
            int r2 = r2 + r21
            goto L_0x04d4
        L_0x04e1:
            return r16
        L_0x04e2:
            r3 = r46
            r1 = r38
            int r2 = r22 + -25
            if (r2 >= 0) goto L_0x04ed
            r22 = 0
            goto L_0x04ef
        L_0x04ed:
            r22 = r2
        L_0x04ef:
            r15 = r39
            r13 = r45
            r41 = r0
            r10 = r1
            r7 = r27
            r11 = r31
            r8 = r32
            r9 = r33
            r12 = r35
            r14 = r36
            r3 = 10
            r6 = 0
            goto L_0x0346
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.FalconKeyGen.solve_NTRU_intermediate(int, byte[], int, byte[], int, int, int[], int):int");
    }

    /* access modifiers changed from: package-private */
    public void zint_add_mul_small(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i + i6;
            long unsignedLong = (toUnsignedLong(iArr2[i2 + i6]) * toUnsignedLong(i4)) + toUnsignedLong(iArr[i7]) + toUnsignedLong(i5);
            iArr[i7] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i5 = (int) (unsignedLong >>> 31);
        }
        iArr[i + i3] = i5;
    }

    /* access modifiers changed from: package-private */
    public void zint_add_scaled_mul_small(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = i4;
        if (i8 != 0) {
            int i9 = (-(iArr2[(i3 + i8) - 1] >>> 30)) >>> 1;
            int i10 = 0;
            int i11 = i2;
            int i12 = i6;
            int i13 = 0;
            while (i12 < i11) {
                int i14 = i12 - i6;
                int i15 = i14 < i8 ? iArr2[i3 + i14] : i9;
                int i16 = i + i12;
                long unsignedLong = (toUnsignedLong(i10 | ((i15 << i7) & Integer.MAX_VALUE)) * ((long) i5)) + toUnsignedLong(iArr[i16]) + ((long) i13);
                iArr[i16] = ((int) unsignedLong) & Integer.MAX_VALUE;
                i13 = (int) (unsignedLong >>> 31);
                i12++;
                i10 = i15 >>> (31 - i7);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int zint_bezout(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int[] iArr4, int i4, int i5, int[] iArr5, int i6) {
        FalconKeyGen falconKeyGen = this;
        int[] iArr6 = iArr3;
        int i7 = i3;
        int[] iArr7 = iArr4;
        int i8 = i4;
        int i9 = i5;
        int[] iArr8 = iArr5;
        int i10 = i6;
        if (i9 == 0) {
            return 0;
        }
        int i11 = i10 + i9;
        int i12 = i11 + i9;
        int i13 = i12 + i9;
        int modp_ninv31 = falconKeyGen.modp_ninv31(iArr6[i7]);
        int modp_ninv312 = falconKeyGen.modp_ninv31(iArr7[i8]);
        System.arraycopy(iArr6, i7, iArr8, i12, i9);
        System.arraycopy(iArr7, i8, iArr8, i13, i9);
        iArr[i] = 1;
        iArr2[i2] = 0;
        for (int i14 = 1; i14 < i9; i14++) {
            iArr[i + i14] = 0;
            iArr2[i2 + i14] = 0;
        }
        System.arraycopy(iArr7, i8, iArr8, i10, i9);
        System.arraycopy(iArr6, i7, iArr8, i11, i9);
        iArr8[i11] = iArr8[i11] - 1;
        int i15 = 30;
        int i16 = (i9 * 62) + 30;
        while (true) {
            int i17 = 31;
            if (i16 < i15) {
                break;
            }
            int i18 = i9;
            int i19 = 0;
            int i20 = 0;
            int i21 = -1;
            int i22 = 0;
            int i23 = 0;
            int i24 = -1;
            while (true) {
                int i25 = i18 - 1;
                if (i18 <= 0) {
                    break;
                }
                int i26 = iArr8[i12 + i25];
                int i27 = iArr8[i13 + i25];
                i20 ^= (i20 ^ i26) & i24;
                i19 ^= (i19 ^ i26) & i21;
                i23 ^= (i23 ^ i27) & i24;
                i22 ^= (i22 ^ i27) & i21;
                i21 = i24;
                i24 &= (((i26 | i27) + Integer.MAX_VALUE) >>> 31) - 1;
                i18 = i25;
            }
            int i28 = i19 | (i20 & i21);
            int i29 = i21 ^ -1;
            int i30 = i20 & i29;
            int i31 = i22 | (i23 & i21);
            int i32 = i16;
            long unsignedLong = (falconKeyGen.toUnsignedLong(i30) << 31) + falconKeyGen.toUnsignedLong(i28);
            long unsignedLong2 = (falconKeyGen.toUnsignedLong(i23 & i29) << 31) + falconKeyGen.toUnsignedLong(i31);
            int i33 = iArr8[i12];
            int i34 = iArr8[i13];
            long j = 0;
            int i35 = i11;
            long j2 = 0;
            long j3 = 1;
            long j4 = 1;
            int i36 = 0;
            while (i36 < i17) {
                long j5 = unsignedLong2 - unsignedLong;
                int i37 = (int) ((j5 ^ ((unsignedLong ^ unsignedLong2) & (unsignedLong ^ j5))) >>> 63);
                int i38 = (i33 >> i36) & 1;
                int i39 = i38 & (i34 >> i36) & 1;
                int i40 = i39 & i37;
                int i41 = i39 & (i37 ^ -1);
                int i42 = (i38 ^ 1) | i40;
                int i43 = i33 - ((-i40) & i34);
                long j6 = unsignedLong - ((-falconKeyGen.toUnsignedLong(i40)) & unsignedLong2);
                long j7 = -((long) i40);
                long j8 = j3 - (j2 & j7);
                long j9 = j - (j4 & j7);
                int i44 = i34 - ((-i41) & i43);
                long j10 = unsignedLong2 - (j6 & (-falconKeyGen.toUnsignedLong(i41)));
                long j11 = -((long) i41);
                long j12 = j2 - (j8 & j11);
                long j13 = j4 - (j9 & j11);
                long j14 = (long) i42;
                long j15 = j14 - 1;
                j3 = j8 + (j8 & j15);
                j = j9 + (j9 & j15);
                unsignedLong = j6 ^ ((j6 ^ (j6 >> 1)) & (-falconKeyGen.toUnsignedLong(i42)));
                i34 = i44 + ((-i42) & i44);
                long j16 = -j14;
                j2 = j12 + (j12 & j16);
                j4 = j13 + (j13 & j16);
                unsignedLong2 = j10 ^ (((j10 >> 1) ^ j10) & (falconKeyGen.toUnsignedLong(i42) - 1));
                i36++;
                int i45 = i4;
                int[] iArr9 = iArr5;
                int i46 = i6;
                i33 = i43 + ((i42 - 1) & i43);
                i17 = 31;
                int i47 = i5;
            }
            int i48 = i32;
            int i49 = i35;
            int zint_co_reduce = zint_co_reduce(iArr5, i12, iArr5, i13, i5, j3, j, j2, j4);
            long j17 = -((long) (zint_co_reduce & 1));
            long j18 = j3 - ((j3 + j3) & j17);
            long j19 = j - ((j + j) & j17);
            long j20 = -((long) (zint_co_reduce >>> 1));
            long j21 = j2 - ((j2 + j2) & j20);
            long j22 = j4 - ((j4 + j4) & j20);
            zint_co_reduce_mod(iArr, i, iArr5, i6, iArr4, i4, i5, modp_ninv312, j18, j19, j21, j22);
            zint_co_reduce_mod(iArr2, i2, iArr5, i49, iArr3, i3, i5, modp_ninv31, j18, j19, j21, j22);
            i16 = i48 - 30;
            falconKeyGen = this;
            int[] iArr10 = iArr3;
            int i50 = i3;
            int[] iArr11 = iArr4;
            int i51 = i4;
            i9 = i5;
            iArr8 = iArr5;
            int i52 = i6;
            i13 = i13;
            i11 = i49;
            i12 = i12;
            i15 = 30;
        }
        int i53 = i12;
        int i54 = iArr5[i53] ^ 1;
        int i55 = i5;
        for (int i56 = 1; i56 < i55; i56++) {
            i54 |= iArr5[i53 + i56];
        }
        return (1 - ((i54 | (-i54)) >>> 31)) & iArr3[i3] & iArr4[i4];
    }

    /* access modifiers changed from: package-private */
    public int zint_co_reduce(int[] iArr, int i, int[] iArr2, int i2, int i3, long j, long j2, long j3, long j4) {
        int[] iArr3 = iArr;
        int i4 = i;
        int[] iArr4 = iArr2;
        int i5 = i2;
        int i6 = i3;
        long j5 = 0;
        long j6 = 0;
        int i7 = 0;
        while (i7 < i6) {
            int i8 = i4 + i7;
            int i9 = i5 + i7;
            long j7 = (long) iArr3[i8];
            long j8 = (long) iArr4[i9];
            long j9 = j5 + (j7 * j) + (j8 * j2);
            long j10 = (j7 * j3) + (j8 * j4) + j6;
            if (i7 > 0) {
                iArr3[i8 - 1] = ((int) j9) & Integer.MAX_VALUE;
                iArr4 = iArr2;
                iArr4[i9 - 1] = ((int) j10) & Integer.MAX_VALUE;
            } else {
                iArr4 = iArr2;
            }
            j5 = j9 >> 31;
            j6 = j10 >> 31;
            i7++;
            i4 = i;
            i5 = i2;
            i6 = i3;
        }
        int i10 = i6;
        iArr3[(i4 + i10) - 1] = (int) j5;
        int i11 = i2;
        iArr4[(i11 + i10) - 1] = (int) j6;
        int i12 = (int) (j5 >>> 63);
        int i13 = (int) (j6 >>> 63);
        zint_negate(iArr3, i4, i10, i12);
        zint_negate(iArr4, i11, i10, i13);
        return (i13 << 1) | i12;
    }

    /* access modifiers changed from: package-private */
    public void zint_co_reduce_mod(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4, int i5, long j, long j2, long j3, long j4) {
        int i6 = i4;
        long j5 = j;
        long j6 = j2;
        long j7 = j3;
        int i7 = iArr[i];
        int i8 = iArr2[i2];
        int i9 = (((((int) j5) * i7) + (((int) j6) * i8)) * i5) & Integer.MAX_VALUE;
        int i10 = (((i7 * ((int) j7)) + (i8 * ((int) j4))) * i5) & Integer.MAX_VALUE;
        long j8 = 0;
        int i11 = 0;
        long j9 = 0;
        while (i11 < i6) {
            int i12 = i + i11;
            int i13 = i2 + i11;
            long j10 = (long) iArr[i12];
            long j11 = (long) iArr2[i13];
            int i14 = i3 + i11;
            long unsignedLong = (j10 * j5) + (j11 * j6) + (((long) iArr3[i14]) * toUnsignedLong(i9)) + j8;
            long unsignedLong2 = (j10 * j7) + (j11 * j4) + (((long) iArr3[i14]) * toUnsignedLong(i10)) + j9;
            if (i11 > 0) {
                iArr[i12 - 1] = ((int) unsignedLong) & Integer.MAX_VALUE;
                iArr2[i13 - 1] = ((int) unsignedLong2) & Integer.MAX_VALUE;
            }
            j8 = unsignedLong >> 31;
            j9 = unsignedLong2 >> 31;
            i11++;
            i6 = i4;
            j5 = j;
            j6 = j2;
            long j12 = j4;
        }
        long j13 = j9;
        iArr[(i + i4) - 1] = (int) j8;
        iArr2[(i2 + i4) - 1] = (int) j13;
        int i15 = i4;
        int[] iArr4 = iArr3;
        int i16 = i3;
        zint_finish_mod(iArr, i, i15, iArr4, i16, (int) (j8 >>> 63));
        zint_finish_mod(iArr2, i2, i15, iArr4, i16, (int) (j13 >>> 63));
    }

    /* access modifiers changed from: package-private */
    public void zint_finish_mod(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = ((iArr[i + i6] - iArr2[i3 + i6]) - i5) >>> 31;
        }
        int i7 = (-i4) >>> 1;
        int i8 = -((1 - i5) | i4);
        for (int i9 = 0; i9 < i2; i9++) {
            int i10 = i + i9;
            int i11 = (iArr[i10] - ((iArr2[i3 + i9] ^ i7) & i8)) - i4;
            iArr[i10] = Integer.MAX_VALUE & i11;
            i4 = i11 >>> 31;
        }
    }

    /* access modifiers changed from: package-private */
    public int zint_mod_small_signed(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i2 == 0) {
            return 0;
        }
        return modp_sub(zint_mod_small_unsigned(iArr, i, i2, i3, i4, i5), (-(iArr[(i + i2) - 1] >>> 30)) & i6, i3);
    }

    /* access modifiers changed from: package-private */
    public int zint_mod_small_unsigned(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        while (true) {
            int i7 = i2 - 1;
            if (i2 <= 0) {
                return i6;
            }
            int modp_montymul = modp_montymul(i6, i5, i3, i4);
            int i8 = iArr[i + i7] - i3;
            i6 = modp_add(modp_montymul, i8 + ((-(i8 >>> 31)) & i3), i3);
            i2 = i7;
        }
    }

    /* access modifiers changed from: package-private */
    public int zint_mul_small(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            long unsignedLong = (toUnsignedLong(iArr[i6]) * toUnsignedLong(i3)) + ((long) i4);
            iArr[i6] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i4 = (int) (unsignedLong >> 31);
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    public void zint_negate(int[] iArr, int i, int i2, int i3) {
        int i4 = (-i3) >>> 1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            int i7 = (iArr[i6] ^ i4) + i3;
            iArr[i6] = Integer.MAX_VALUE & i7;
            i3 = i7 >>> 31;
        }
    }

    /* access modifiers changed from: package-private */
    public void zint_norm_zero(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = 0;
        int i5 = i3;
        int i6 = 0;
        while (true) {
            int i7 = i5 - 1;
            if (i5 > 0) {
                int i8 = iArr[i + i7];
                int i9 = iArr2[i2 + i7];
                int i10 = ((i6 << 30) | (i9 >>> 1)) - i8;
                i4 |= ((-(i10 >>> 31)) | ((-i10) >>> 31)) & ((i4 & 1) - 1);
                i5 = i7;
                i6 = i9 & 1;
            } else {
                zint_sub(iArr, i, iArr2, i2, i3, i4 >>> 31);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int zint_one_to_plain(int[] iArr, int i) {
        int i2 = iArr[i];
        return i2 | ((1073741824 & i2) << 1);
    }

    /* access modifiers changed from: package-private */
    public void zint_rebuild_CRT(int[] iArr, int i, int i2, int i3, int i4, FalconSmallPrime[] falconSmallPrimeArr, int i5, int[] iArr2, int i6) {
        int i7 = i4;
        int[] iArr3 = iArr2;
        int i8 = i6;
        iArr3[i8] = falconSmallPrimeArr[0].p;
        int i9 = i2;
        int i10 = 1;
        while (i10 < i9) {
            int i11 = falconSmallPrimeArr[i10].p;
            int i12 = falconSmallPrimeArr[i10].s;
            int modp_ninv31 = modp_ninv31(i11);
            int modp_R2 = modp_R2(i11, modp_ninv31);
            int i13 = i;
            int i14 = 0;
            while (i14 < i7) {
                int[] iArr4 = iArr;
                int i15 = i13;
                int i16 = modp_ninv31;
                zint_add_mul_small(iArr4, i15, iArr2, i6, i10, modp_montymul(i12, modp_sub(iArr[i13 + i10], zint_mod_small_unsigned(iArr4, i15, i10, i11, modp_ninv31, modp_R2), i11), i11, i16));
                i14++;
                i13 += i3;
                modp_ninv31 = i16;
                int i17 = i2;
            }
            iArr3[i8 + i10] = zint_mul_small(iArr3, i8, i10, i11);
            i10++;
            i9 = i2;
        }
        if (i5 != 0) {
            int i18 = i;
            int i19 = 0;
            while (i19 < i7) {
                zint_norm_zero(iArr, i18, iArr2, i6, i2);
                i19++;
                i18 += i3;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int zint_sub(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = -i4;
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = i + i7;
            int i9 = iArr[i8];
            int i10 = (i9 - iArr2[i2 + i7]) - i6;
            i6 = i10 >>> 31;
            iArr[i8] = i9 ^ (((i10 & Integer.MAX_VALUE) ^ i9) & i5);
        }
        return i6;
    }

    /* access modifiers changed from: package-private */
    public void zint_sub_scaled(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        int i7 = i4;
        if (i7 != 0) {
            int i8 = (-(iArr2[(i3 + i7) - 1] >>> 30)) >>> 1;
            int i9 = 0;
            int i10 = i2;
            int i11 = i5;
            int i12 = 0;
            while (i11 < i10) {
                int i13 = i11 - i5;
                int i14 = i13 < i7 ? iArr2[i13 + i3] : i8;
                int i15 = i + i11;
                int i16 = (iArr[i15] - (i9 | ((i14 << i6) & Integer.MAX_VALUE))) - i12;
                iArr[i15] = i16 & Integer.MAX_VALUE;
                i12 = i16 >>> 31;
                i11++;
                i9 = i14 >>> (31 - i6);
            }
        }
    }
}
