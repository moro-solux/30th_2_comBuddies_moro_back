package com.example.moro.global.util;

import lombok.Getter;

// 144개 컬러를 반영함. hexcode를 rgb로 자동 변환하는 로직을 포함한 전체 enum 코드.
@Getter
public enum ColorPalette {
    // --- 1그룹: pastel (1~48) ---
    PINK_1(1, "#F5D8E0"), PINK_2(2, "#EEB6C6"), PINK_3(3, "#E895AB"), PINK_4(4, "#DF7DA8"), PINK_5(5, "#CD5F80"), PINK_6(6, "#B34166"),
    ORANGE_1(7, "#F7E6BA"), ORANGE_2(8, "#F3D6A5"), ORANGE_3(9, "#EEBB84"), ORANGE_4(10, "#E99F65"), ORANGE_5(11, "#D1814B"), ORANGE_6(12, "#B76B40"),
    YELLOW_1(13, "#FCF9CA"), YELLOW_2(14, "#FAF5A8"), YELLOW_3(15, "#F9F189"), YELLOW_4(16, "#F8EE74"), YELLOW_5(17, "#F1D95B"), YELLOW_6(18, "#EBC251"),
    GREEN_1(19, "#D8EFC5"), GREEN_2(20, "#BFE3AA"), GREEN_3(21, "#A8D98F"), GREEN_4(22, "#92CD76"), GREEN_5(23, "#7EC35E"), GREEN_6(24, "#64A743"),
    BLUE_1(25, "#DEEFFD"), BLUE_2(26, "#C3E4FC"), BLUE_3(27, "#A9D7FB"), BLUE_4(28, "#91CAFA"), BLUE_5(29, "#7CBDF9"), BLUE_6(30, "#5C97C7"),
    PURPLE_1(31, "#E7E6F8"), PURPLE_2(32, "#D2C0D6"), PURPLE_3(33, "#C5A6DA"), PURPLE_4(34, "#AF84CC"), PURPLE_5(35, "#905DB0"), PURPLE_6(36, "#744493"),
    GRAY_1(37, "#F5F5F5"), GRAY_2(38, "#E0E0E0"), GRAY_3(39, "#CCCCCC"), GRAY_4(40, "#B3B3B3"), GRAY_5(41, "#999999"), GRAY_6(42, "#7F7F7F"),
    BLACK(43, "#000000"), WHITE(44, "#FFFFFF"), SILVER_1(45, "#DDDDDD"), DARK_GRAY(46, "#444444"), OFF_WHITE(47, "#FAF9F6"), SILVER_2(48, "#C0C0C0"),

    // --- 2그룹: vivid (49~96) ---
    VIVID_RED_1(49, "#DE3323"), VIVID_RED_2(50, "#B1271A"), VIVID_RED_3(51, "#851A11"), VIVID_RED_4(52, "#580E08"), VIVID_OR_1(53, "#E26F2E"), VIVID_OR_2(54, "#B55923"),
    BROWN_1(55, "#874218"), BROWN_2(56, "#5A2C0D"), V_YEL_1(57, "#FCFE57"), V_YEL_2(58, "#C9CB44"), V_YEL_3(59, "#979831"), V_YEL_4(60, "#65661E"),
    V_GRN_1(61, "#6AC83E"), V_GRN_2(62, "#4E962C"), V_GRN_3(63, "#32641A"), V_GRN_4(64, "#163209"), V_BLU_1(65, "#4767F5"), V_BLU_2(66, "#2E4C93"),
    NAVY_1(67, "#1D3362"), NAVY_2(68, "#0B1931"), V_PUR_1(69, "#8C25F5"), V_PUR_2(70, "#6A1BC3"), V_PUR_3(71, "#481092"), V_PUR_4(72, "#2F0761"),
    MAGENTA_1(73, "#E04997"), MAGENTA_2(74, "#B33A78"), MAGENTA_3(75, "#862B5A"), MAGENTA_4(76, "#591D3C"), D_BRN_1(77, "#905636"), D_BRN_2(78, "#6D3F27"),
    D_BRN_3(79, "#4C2A19"), D_BRN_4(80, "#26150C"), M_BLU_1(81, "#6197C7"), M_BLU_2(82, "#487295"), M_BLU_3(83, "#304C64"), M_BLU_4(84, "#172632"),
    OLIVE_1(85, "#979846"), OLIVE_2(86, "#727334"), OLIVE_3(87, "#4C4D23"), OLIVE_4(88, "#252611"), TEAL_1(89, "#78C9CB"), TEAL_2(90, "#599798"),
    TEAL_3(91, "#3B6565"), TEAL_4(92, "#1C3233"), D_CHARCOAL(93, "#242424"), SNOW(94, "#F7F4F4"), MID_GRAY(95, "#999999"), D_BLACK(96, "#333333"),

    // --- 3그룹: nature (97~144) ---
    SKY_1(97, "#DFF6FD"), SKY_2(98, "#CBF2FD"), SKY_3(99, "#ABE9FE"), SKY_4(100, "#8EDBFE"), SKY_5(101, "#5ABAF4"), SKY_6(102, "#1985C3"),
    CYAN_1(103, "#DFF6FC"), CYAN_2(104, "#D2F2FC"), CYAN_3(105, "#A2E8FB"), CYAN_4(106, "#68D3F8"), CYAN_5(107, "#40B7E7"), CYAN_6(108, "#0D8ABB"),
    MINT_1(109, "#D7F1F2"), MINT_2(110, "#BAEBE6"), MINT_3(111, "#9EE2DB"), MINT_4(112, "#5BCEC6"), MINT_5(113, "#3BB9BF"), MINT_6(114, "#10809C"),
    AQUA_1(115, "#D2EEEC"), AQUA_2(116, "#C1E6E0"), AQUA_3(117, "#8FDBD2"), AQUA_4(118, "#6AC6BB"), AQUA_5(119, "#3FB9A5"), AQUA_6(120, "#11788A"),
    KHAKI_1(121, "#DBE8D6"), KHAKI_2(122, "#C6D9B7"), KHAKI_3(123, "#99BB7F"), KHAKI_4(124, "#699860"), KHAKI_5(125, "#4C7B4D"), KHAKI_6(126, "#3D6547"),
    BEIGE_1(127, "#EAE5D7"), BEIGE_2(128, "#E5D6BD"), BEIGE_3(129, "#D3C09C"), BEIGE_4(130, "#B4A17D"), BEIGE_5(131, "#978161"), BEIGE_6(132, "#5F4E3E"),
    SUNSET_1(133, "#F2DFB6"), SUNSET_2(134, "#F3C386"), SUNSET_3(135, "#F0AA58"), SUNSET_4(136, "#D66D44"), SUNSET_5(137, "#B95145"), SUNSET_6(138, "#884642"),
    EARTH_1(139, "#F1CBB0"), EARTH_2(140, "#EFB481"), EARTH_3(141, "#E38373"), EARTH_4(142, "#CE656C"), EARTH_5(143, "#96547D"), EARTH_6(144, "#7D466A");

    private final int id;
    private final String hexCode;
    private final int r;
    private final int g;
    private final int b;

    ColorPalette(int id, String hexCode) {
        this.id = id;
        this.hexCode = hexCode;
        this.r = Integer.valueOf(hexCode.substring(1, 3), 16);
        this.g = Integer.valueOf(hexCode.substring(3, 5), 16);
        this.b = Integer.valueOf(hexCode.substring(5, 7), 16);
    }
}