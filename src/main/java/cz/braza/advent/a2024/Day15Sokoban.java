package cz.braza.advent.a2024;

import cz.braza.advent.AOCHelper;

import java.util.ArrayList;
import java.util.List;

public class Day15Sokoban {
    public static char[] MOVES = ("^^<>v^v^v>><<^<^<>>v>vv^<>^><v<><v^<vv<v^^<v^>^>><^>>>^<^v>^>v<^<<^>^<^v<^<v<<>^vvv^<^^v^v>^<><v>vvv<<<^><<^v^^^vv^v<vv>v<><><<v^v^>^v<<v^<><v>^>><vvv>v^vv<>>>><v^<>v>vv>v^>><>vv^^<v<<<<vv<><<^^<><<<^v><<^v^vvv^v<v>v^^vv^vv><<<<v>>v<^^<^vv<^^>v^^>^><^^v<^<v>^>v<^^^>>><<>><v^>^^>vv<>^^v>v><vvvv<<>v<^<v><v^<>^<><<^>^^vv><vv><<<<v>^<v><<<v<v><>>v^^<v<>vv>^v>v><>^vv^^<><>^vvv^vv^>^^v^^>>v<>>>^>><><>v>v<<^v^vv^<^vv>v<v<v^<^^v<^<vv>>v^v<vv^vv>^>><v>^>^<v^v>^<v^>>v^v^><v<v^><v><><^<>v^<^^^v<>>^v^^v^<>v><^<^v^^vv<><vv>^v^<>vvv<><>><^<^^<^^v>vv>>><vv^v><<<<v<vv^<<>^^v^v^><v>vv^>v^v<vv><><>^>^>>><>^^v^^<>v^v>>v^<>v<^>^^v>^<vv^>>v>v^vv<^>v>v>>^<><^^<^v<v<vv^<>^<^>^>>vv^<v><^v^>^^<v<<^>>^><>>^^<^^><<<<<^<^>>>^><>>v^^vv<>>^>><<<<>^>v^vv<v^<v<^><v>v^vv<^>^<<<<>>v<>^><<v><vv>^v^vv^v<><^>^v>v<^>vv^v><<^>>v^>v<^><<>>>v<<<<^<<vvv^^>><>^<>v>^vv>^<>^^^vv<><^>v<<^><v<<^^<<<^v<<^<><<^v<^v>^v>^>^v<><v^<<>v<^>><<^<<vvv<vv<<><<<<<^<>^^^<<>>v><vv^>v^v<^^v^^<v^<<>^v<vvv<>v^^<>v>v<><>vv<><^>><<><<><^>^v<^>v^<>>v^" +
            ">^<>v<>^^^><^^^^^>^v<^>v<v>^vvv<<<<^v^^<>^vv^<^>v>^^^v^v^<<<><^^v>^v<<>^v><^<>^><v>^v<vv<^<<^v<v><<<^<>^<<>>v<^<^^^^v>>>>v^^<^v>vvvv>v<v><v^^<><^vv<><<>^^vv<>^^^<^^v>v<<>><^^<<<<>^^v^^>><^^<v^>vv^>^v<v<>^^<>vv^vv^>^>v<v<<>><^<<>>vv<>^<^^>v^v^^vvv^<v<^v^>>v<<><^^v>>vv<>vv<^^v>^^>v^v^vvv><v>^<vvv^vv<<v>^^^^v>^<<>^^<v>>vv><<<>>vvv<^vvvv<>^<^<<v^^^^v<^<><<<^<^v<^v^^><<^>^v>v^<>^<<^<>>><>^v>^^<><^><v^<><v<^v>>^<<<<v>><>>^<^v<<v<><vvv^v^>>>v^^<>>>^v>>v^vvv<>><v>vv><><v^^^^v<^>v^<<>v<<v^<^<vv^^^^v^<vv<^<v><<<>>><v<v^^v>>^v<<v^<>^<^vv^^^<^<^v^v^^^^>v<vvvv<v^<^<>^v^>vv>v^>^<^>vv>^>^<^v>>v>^v<<v^v^><vvv^^^v>vv<>v><><^>>>v^v^^<v^<>vv^vv<>^>v>>^<v><<<v>v^^vv<<<v^^<v^^>v<v><^vv>>><^>^^<^^v^>>v>><vv><^<>>>>v>>^<v>^>^<^<>>>^<^^<<><<v<v^<^v>^v^>^>^>^^><>>vvvv^>>vv^><><<<<^vv<vv^vvv^<^vv^<^<v^^<<vv<^^><>>v>^<<^<><v<v^v^^<>^>v>^<^<v^<>v^^^v<v<^><^<><^>v><v><<vv<<v>>v<vv^<v<<>^v>>vv^<><<>>v><^v<^><<<v>>>^>v^><<^v<vv>^>>^>vv^^^^^>><vv>^<<v>>^>v^<<v<><<^^^vv^^v<^<v^v>><v^^<v>>^<>^>^<>v^^><><v<v^^v<v>>>^^vv" +
            ">><>^^^<>v^>v>v<vv^^<vv^<>^<><^^>^^<^^>^<>^><<>>^v<<<^^>v^<><<v<^>v^^<^^<><<vv>v><^<^^vvv^^>v^<v>>v^^>^v^vvv^v>^>v^><>^><>>^v>>v^<><<<<>><v>>^v<>^<>^>^^^>^^>^>v<^^^^>>><v^v<^^><^<^vvv>v^^>^^^v<^^<<v>><>^vvv^<^^v<^^>>vv^^<<^^^>v>><v>^v^v^v<>>>^v^<^<v>^v<^>v^^>vv<^vv>^><vv>v^v^^v^v<>^<>>^>^>v^<vv>>>>vv<<^^^<<<<^vv^>vvv^<v>^v>v^vvvv>>^^^>^^>>^<^><>><v>>v^v<<<^<vv^v<v<<v<>^<v>^>^^^v><>^<^<v^><>vv^<><>>^<<>^^>><><<v<>>vvv^vvv<^><><^^v^><>^^^>^v>^v<^vv><<v^^<vv>^^^>v^^<vv^><<^<vv>^^^^v><^<<v>><<^<^^><<<>v>v^^^^<^^^>^^>v^^^^^>v<>^>><^<><<<>^<v<vv>>^^^vv>v>>>^v<^>v><vv<<<<^<<^^<<<<v^<>^><vv<v<><<>><>>>>>>^<v>>^<<<><>>>^^v<v^<^<vv<<<vv<>v^<^v<<<^^<^^v<>^>>>^v^<><<v^^<v<^^><>^>v<<>vv^>>^v><<<<vvv^v>^<>>><^<>v>><^<<<v>v><v>vv^>^^<v^<>v>v^<>>vv<^v<>>><^><^^v^>>^>>v^v<v<v<^^><><<><v^^<<v^v<><vv^>>vvv^<>^v<^<vvv<v^<^^v^v<v><vvvv<><<^v>vv><^^v<<v>v>^<^><v>>^<v>>vv>>>><^<>v<<<^v<>>>^>>v<v<^<v^>^<v<^^<>v<<>^^><v><v>^<vvv^><>v>>v<<>v^v^<v^><>>>v>^><>><><v<^v<<>v<><<>^^><vv^<^v<>>vv<^>^^<>^^><^v^>vvv>^<<" +
            "^^>^v<><>>>>><<vvvv<>^><<v^v>v<<>v^<^>><>^<^v^><<<vv^^>v>vv<<><v<<<^^<<>>v>v^>^<<^><v<><><^>^<<>v><^v<v^>>v>^^^^>>^^^v<>>v<<v^v^^<v<><<v<<v<><<<>^<<>>v>^<>^^<><^<<v>>^^v^^<v<>^^<v>v<^^v<<v<<<<<vv<>^<v<>vv^v<v<<v^vvvv><^<vvvv<^>vv>v^v><<<>>^^vv<v^^vv<vv<>v^^<<v^<^><v^^^<<<>vvv<<<v>><^v>>><v><v<>^^<v^v>^><><^^>^>>v^^<>>v>v^>>><<^v>vvv^^v<^v^v<<v<v^^^^><><v>^vv<^v>v<vv^<<v<v<>>^<>^^>^v>^<^>>^<<v<v<vvv^>^>v<v^v^v^<v>v>><>^<<^<^<v^<<^<>^vvv^^<v<<^^><vvvv^>>^^v^v<^<>v><<^v^^>v>>>><<v>>vv><<^v^<v^>vvv<<>^^>^v<>><>^>^>v^^v^^<v<^>^<^v<>>^<<<^vv^>>><<v^v^>^<<<v<^^><^<v<^^^><^^<^v^<^^^<<<<<^v>^<>^<<v>>>^>^><<^><><v>>>v<>v>^v^v>^><vvv^v<^<<^v^<<>^vv^v^^>>>vv><^<>^^vv^>v<<^v><>^>^v<><<>>v^<<><^v>>>^vv><<>v<><^<v>>>^>^^vv<>v<<>v>^<<>>^^><v<v<^>v^v><vv><vv^v>>v^v<vv>><>>^<>^><<^vv^^>^vv^^v>>v><<v>vv<<v^>>^vvvv<<v><>><>>^><^><<vv<<><>v^^^v<<v^^><v>^<v^<v<<vvv<v<<<^vv<vv><v^>>vv^^<^^^>^<>v^^><>v<>^><<>^<><^>^<^v>v^v<<>><^v^<^>>v^^<v<^v<^v<v>><vv^>>^vv^^v><><<^v^v^^>^<<<>>>^>v<^<<<v^^^^>v>>vvvv>vv^<v<^>" +
            "<<v<>^^<v>vv<v<vv^v<>v^>vv><>^v<>>^<<^v>>>vv>^<><v^<vv<vv>>v^<<>>><<vvv>^^>vv>>vvvvv^v^<vv<>^<^<<^^<>>^^<^>v>v^<>>><<v^><^>>>vvv^<v^<^><^<<vv^^^<^v<^>><^^vv<<^>>>>>>><^v<^><<<><^<v^^^^^v<<<>^<>vvvv><^<vvv>>^<^<^^v>v>^^>^<^<<vv>v^<v>v^>^vv<v>>v^v^<^<v>vv><^<^>>v>v<<<><>^v>^^v^<<^>>v^^^><<<>>^^<v<v<><<^v^v^<>^^vv>vvv<v^>v<^^^v<v<><<^<>><v<>><v><^^<<^v^<v><>v<v^v<^^^>v>v<^>v>>>^>v<>>^>^<vv><^>>>^<<v^v>>^^^<<><>v^^^<v><vv^vv<^^vv<vv^>><v^^<<>v^v<>>v<vv^vv^<<>v<>vv^v>>^vvv^v>^^<v>v^^v>vv<>^^>v>v>>^><^<<>>v^v^>^^^<<vv>>>v<v>>>v<>^>v>^^^vvvv<v^^>>v><>v<<>^v>^^v>><>^>><v<v><v^>^<^<^<<^>v>^v<v<^>^^>^<<^v^<>>^^^v>vvvv<vvv>^^vvvvv^v<<>>><v>vv^<<<^^^^v<^>><>^>>>^^^^v^<<>>v>^^v^<>>><<<^>><v<^>vv<><<>>^v>v><<^v<>^v<^>>>>>^>>^v^>v^^<v^<>>^^vv^>^^^<>^^vv<<<v^v>>^<<vvv^>v<^^v<v>vv<><>>^^<>>>^vv^v^^<v<<v<v>>><>><^><>^>^^<><^>v<v<>^^><v<<<^^v>v^<>v^v><^<v><^v>>^<^>>^>vv<<v<<<^^>v>>^<>v^<^>>>v^^>>v<^^^>^v^<>vvvv<<^><v^vvv>>^^vv^^<v><>^>>><>v^>v<><v^^>>v>^><><<>vv<^<><><<v>^<v>><<>>^<^^^^>^vvv<^>><<^^<>^>>" +
            ">^v>>>^<^v^^<^><><vv<>vv>>vvv<^<^^v<^<v^<^v>>vvv<>v>v<<^^<><v<<v>^<v^v^vvv>v>v>vv<v>vv<^><^<^^<>vv>v>vv^><v<vvvvv^^<<>v^>>>^^^>><>>^^v><<>vvv<<v<<^^>>>^^><v>>v^^<^>v>v<>^vvv^>>^>>v<^<>v^<>^v<^<>^<<<<^v^v^<>^>v<v^>>^<>^<vv>^<>v><v<vv<><^v>>^<^>^<^<v>vv^v<><>vv^><^><<vv^>v><>v<v<v<<v<^vv<^^v>^<><v>>^<<^^^v<^v^<^v^vv<^><v^vvvvv^>^>v>^^>^><<>^>^v^<^^>><v<>v>v<>^v^>v^v^v<><<v<><^^<<<^>v<v^^^v^<v^^>>^>v>^>>>>^^>v<^vvv<<>^v^<><>^^><^v>v>^^^><><^v><><<>><vv>v^^><v<><<<v>>><>v^<^<^><<>v<v>^vvvvv>^v><<v<><<v>^<^><^<^v^<v><<<v>v<>^^v><^^>>v^<<<>>^vvv<<<<<^^v><<<><vvv^>vv<^v^v>^<v<>><v^^v^^>^v<<><<<><<>v<>>>^>>^^>^vv^<^v>vv><>^<<<<>><^<v^<^>vv<v>vvvvv<>vv^^^^vv^<<v><^<^<^>v^v^^^<>><><<<vv^^>>><vvvv<<<v<^><^v>^v^vv<><^v<<>^>v>^<<v><>>><>vvv><<v<>^>v><>v><v<<^v^>><^<<^><^><<<^<v<<>>v^>^<v<vv^v^v^<^v<v^>v<v^^>^v<<>vvv^^>>v>^^>v>>^<>>><<^^^<^>v>><<>v^<vv^>^<<v^>v^><>^>^^v>v^^<>^^>vv>vv<<v<^<<^<<^^>^<>^^vv^><<<vv^v<^v<>><<<<^>^><>>^vv>^v^v<vv^<<v^^>^><v^>>^><^^>^^<^^v^vv<><^>v>vv<vv<<<>^^<v>^><vv^>>>>v" +
            "v<<^v>>vv^<><>>v><>vvv<>>v>^>>>^<>^vvv>^>^^>><v>^^<v<<v<><>vv^>^^<><>>^<vvv^vvvv<^<^v>^>>^>>v>^^v>v<>>><v><<<<vv><^v^<<^vv<v><<^>^>v^>^^v<><vv<^v^>v<^v^^<>><^>><^<<<^v^^^v<<^<>>v<^>v>^>v^v^^^^v<^<<v^^<<^<><v>^^v<v^<<<^<<vvv><v<<>^vv^v>v^^^>v><><^^^v><>^>^>^>><v^<<><^^<v<v^>^>v<<<<<<<>vv<<vvvv^vv<<<v<><<^>v><^><^>v^v^vv>vv<^><^><<^<^<>v>^v^^>><v><>^v<vv>>>^^<^v^>v<><<>>^v^^^v^^vvvv^>v<><v><^^^^>^<<v>>>vv^^^^<v><vv<^>v^<>v<<v<<>^v>>><<v^<>v>v<<^v^v^>^><v^<>^v<v<<^^^>^^>^<^<><>^v<><^^>^<<^^^<^>^>vv>^^^>^<<>v<v^<^>v^>><<>vv<<<>vvv^v^v<><^^^v<^^>^v^v><<<^>^vv>v^^vv<v>^<^>><v>^>vv^<>v^>v>>><vv<^^v<v>v>^vv>^^vvv<^<vvv^>>^<vvv^<v<<^>v<><>v^v><><^>^><<<>^><>>^^<<<^>>><<^v><v>^^>>>v>^v^v^>v<v^<<^<^vv^^>>^^^^<>>>v^v^^^>^^>^>v^vvv>vvvvvv>v^^v>^^>^vvv^<vv><>^vv>^vv<v^^<><<>^^<v>>>><<v>v^^^v>v><v<v<>v><<vv^<v<<^<<<^>^v<>v<v>><<v^>v^<>^>v<^>^^>v^<v><vv>vv^<v<^<>><^<^<>^v^<<<<^<>><v<<^>>><<<v>>>>>v><<><v>^^<><^>v<<^>^^^<^v>v><v>v>^^v<>v>>^^>v<>^^^vv>><^>^>vvv>>^><<^v><<^^<^^^^^^v^v>^>^>v^v><v^<>^>>v>>" +
            ">^><><<>^<>^^<^^^vv><>^^>vvv^>vvv<v^>><<v<<v<vvv<vv><^v^^v><<>>^<<<<^<v>^vv<^><v>>><v^^vvv>v^v>>^vvvv>><<^vv^^>^>^<^v>^v><<v^><^^v>>><v<<>^v><v<><<><v<^^>>>>^<^^^^^^>^^<^v^v>>^^^<>^><<>^>v>>vv^^vv<<>v>>^^^v^v^^^><>v<^^^v<><>>vv>^<^^><v>^^^vv>^^vvvv^<>>>^^^><^v<>v^^<vv<<v>v>^>v><><<>>vv<><<>vvvv^>v^v<^^vv<v^>v<><<<<^vv>><>v<>><^<<^<^^^vvv^<><<^v>v^><v><^^><<v^>v<v<>^>v><vvv<^<>>^>v^v<v>v<<<vv><<v<^v<<>vv<vv>^<v>><v^v^v^>v<<^v^^^><v<v^v<v^<>>>>vv>>>v^>v<v>^v<vv<^>^<^^^v>vv>vv>><<>>>^<^>^<<^<^<vv<>><>vvv>^^<v><v^v<<^<>vv<>v>v>v^>>^<^vv<vvvv^>>>v>v^^v<v<<>>>^^<>^><^>>>^><^v<v<v<^<<^><<<<<<v>>^<><<<><^>vv^^>v>^>vvvvv^>>><<^><^<^^>><>v^<^v>^<>v^v^v>>v<<^v><>v>v<v<^vv>^v<^v>vv>v>^<><>vvv^<<v^^^^v^>^^<v>^<>vv^><>v^>>^<vv<^^v>v^<^vv><>v<<^><^>^v>vv>>>>^^^<<>>v>>^^v<>>><<^v<v><v>><<^>^<v<>v^^<^><<><>>><v<v^^vv<<^>^v^<<>>^>v><>>^^>^^v>>^<>v^<<v^>v<v>^^^v>>^v^<>^<^><>^<vv>>^<^><>v>vv>v^<><v<<v<<<^^^>>v^^vv^v><>>>>>>>v>v<v>v^><v^<v^^>vv^^><<^<^^<v^^<><>>v>v<v^vv^><<^<><^<v<<^v<v>>><<^<v<v>><^>v^<>^" +
            ">^v<^^^^<v>vv<>>v^v>v<<>v^<>vv<<<>^v<<><v<v>>v^v^><v><^>>v<v>v^v<vvv^>><<v<<<<v^^><><^^>^><<v^^^v>^^<<>^>^^<<<^>>><v<><>>>^vv>>^v<<v^<>v^>^<^^^^<vv^<<>^vv<><>^^>vvv<>v<v^^v^>^^><^<>><v<v><>v>v^vv>^v>^>v><><v<<vvv<>^>v><>>^<>v^^^^><v^<<<><v>v^^>^>>^>^v<>v<>^^^v^><<<<v<<^>v<<><v>>v^>v^^>>^>>^^><v<^^v>>>v<>^v<v<<<v<>v^<><v<<<<^^vv<^><<>v^<<v>v^vv<<v^<vv><v^^^>vv^v^>v><<^>>>^>^v<>v^<^vv<>><^^v<>vv>v<<^>>^v>v^<^v>vv^^^vvvvv><>^<v>v><><^^vv^^>^><>>v>v^<^vvv><<v<<vv>vv<<v^^<>^>><v^v<v>>><<<v<^^^<<^<>vv^>>>v><>>vv^<v^^>^v>^<v^v>^^>^^^^>v^^><><<^<^><^v><><<^^><vv<v><<^vv<>v<>>v^v>><<v><<^><<^^^v>v>>>v^^<^^v>>>v>>>^<<vvv^><<v<>v^><^>^<<v><<<^^>^>v^<v>^vv<^^<<<<>^^^^<<>>^^<v^^v>^>vv^vv^><^v<^>><><^<v^^^v^vvv^<vv>v^^>v^^>v<>v<v^^>>v^<vvv^^^<>><^>>><<<<v^^<>v^v<v>^<<v>v<^<v^>v>^<<<><<v^>>>^>>v<>>v^<v>v<v>>v>^^vv<>^vv^^v>v<<v^^<<v<vv>^v<>><v>^><<<^<><>>vv^^vvv^v^<^v><>^^<<<>vvv<>v>^^><v<vv<<v<>^v<v<^^>^v>^v>>><>^>v<vv^v^><>^>^v>v^^v^^>^<<>>^<<<<><<vvvv><><<>>v>^v>>>^v>>v^<>v<vv<^^^<>v^>v<>v^^<^v<v^<" +
            "<<><v><^<><<><<vv^<<>>>v><v<v>>^<<^v>^<^vvv^v^>><>><>vv<^<vv^v<^><^vvv>>^>v<>^^^v^><<^<^^^<v><>vv<^>^>^>^><^><<^>><>>v<><>vvv^v^^<<<>^>v>^>^<v^v^^<^v^vvv<vv^^v^>^^>vv<^vv^^^>>><^<>><>><<>v><^vv<<<<^<<v<<^<<>>^^>^>>^^<v<<^<^^>v>^v^>v^<^>>>v^<v^><^vv>^^>>^<>>>^>v^<<>>>><><<>>>^>>^>>v^<^vv<<v>>>>v><><v<^v<<><v<>v^><v<v^<<>^<v><^^>v<>^>>v<v>vvv><><v<<>>v<>^v>^^<<><v<<>vvv><<<^>><><<vvv><><vvv^>^>^v<<><vv><<<<v>vv^>^v<v<<^v>v<^<<vv^v>>><^^>^vv<vv^>^vv<v<>^><>v<<<^^<<^^^^>><>>><>>^v<<v>v<v^<<^<^<<<v^v><<^><^<<v^v^>>^^<^><<v^^^^vv^v^<v>v><<<vv^>^v>>v<^><<>vv>vv<<v>vv^>>v>>><^v^^v<v^v>v^^<^>v<>vv^>^<<v^>>v>^>^>><>>><v^^>^>v^v<<^<^^<<^>^<><<>><>^>v<^^<<>^<v>v<^v^^v<<^^>v>><vv<v<v><v>v<^<^>^>^><>vv><<^<v<v>^vv<>>v^><>><<<>v>vv><v>>v^>^<<^^><>v^>>><^>>>>v^v<v>^v^<vv^^^<<<^<<^^<>v^^<<v>>>v<vvvv<<^v>vv<vv<v<v>>v>^vv>>^v><>vv>v^v<^^><<<^<^>>v<v<^<<>^^vv>^v<<v>^^<><>>v><^><>^^<v><^<><<>>v><^<^><^v>^<v<^>^v<vv<^v<><<vv<>v<>><<^^v<^v<>v^^>v^^^vvv>>>>><>>v>^v<>>><>>^>^vvv><v^><vvv>><>v>vvvv^<v<vv<v<v><>" +
            "v<><<<^<>^^^v^<<>v<<><><^>^vv^v<^v<<><^>v><v<^<v><^<vv<^^><^>>>><>^^<^<v><v>vv><v<>>^<<vvv<<^^^vvv^^v<^^^v<<^<v>^>^<><v<v<>>vv^v^>^^<>^>>v<vv<<v<>v<>><^>vv<v<<>><^>><^^<<v<v<><>v>><v^v<>>^v>><v^^^^v<v>>vv<v<>>vv>^v<>^^vv><^v>v<v><^v^^v^v>^^^>><<<^<vv^^v<<>v<^<v<<>>v<>>^>v^^>^><><^<>v^<v^<<^v><v^>^vv>^v>vv><^<>^vv>^<>>>v<^>v>v^<^>^><<<>>^>^<^v>>v<<^<v>>>>v>v^^>vv<v>>>><<<^v^<<v<vvv<v<^^>^^^^^v>^v^>^vv<<<^^^>^v>^>^><v<v^^>^>^v^><<<v><<><vvv>vv><v>vvv<^v>>^>vv><<<v<v<^>v^^<v>^^<^vv^<><<<<v<vv>^v>^<vvvvvv>>^^<^<>v^><^v>v<<<^<><v>>^v>v>>v^>><^<v^><<^^^><^>v^v>vv^>v>v^v<<^v<<><^<v^^<v^v<v>^<^vv^>>v^^v^<^v^<>v<^^v>v^v^^^<^v>>v>><<^<v^vv>>^v^<<<>^<v>v<vv^>^>^v>v<<<<<<>><vv^^vvv><<>vv^^v<v<^vv>v>vv<v>v^^v<>v>^v>vv<<<^>>v>^><>^^<vv>vv>v<>v<^<v>>v^<<^^<^v<v<^<^><v<vv>^vv<<^^<^>><><^<^v>^<vv<v^vv<^>vv^vvv<>^>>^^^>^>^>^><^><><><^v>v<>^v<<<>v>^>>v^vvvv>vv<^v><^><v>>^>>>v^^^v^v>^<^<<v<^<<<><<>>>v>^>>><>><<vvv<v^<>v^<^<<^^<>v^v>^^^^v<v><^<<>^<^v^vv^>>^<>v>>>vv><<>><>^v^>v>^^^vv>>^^vv>v>>v^^>v<<><>v<<>" +
            "<>><>v^^v<<vvvvv^v>v^^><<^^>^>^^v>><<>v^<^^^><><>v^v<v^^<v^><>vv>^<^>^^^v^<<v><><>vv><<v>^v><v>>^>^>^^<<v>^>^^^vv>^<><>><<<vvvv>>><vv>^^<v<v>>^><<^^<<^<<><^vv>^^v>^<>v>^><v^v^v<<<v<^^<<><>v^vv<>>><^vv^^v^vv<<^>vvv>v><^v><^v^^<^<^<>^^>>^v^><<<<>^^<<<vvvv^>>><>>>>v<>>^<>>v^>^<^^v>>^>v^^<^>><><><^v^v>v<^^>^>vv^v>v>v<v>^<>>v>><>^<>>v^^vv^<>><<^><^vv>>^><<v^^><><<>^^<<<<^^<v<>v^<^<>>v>^vv<vv^<<^^<>vvv<^v<<v^vv^vvvv<^v<>v^^^>v<<v<v^>>>^^>^v^<^>v<^>v^vv>^><<v<^^>^^<>><^v^<><<<^><>><^<<><^<><vvvv>v^>><>>vv>>^>vvv>><>>vv<^<v^>>^>^v<<^^<<^>^>v^<<^<^><>^v<^<<><^v^<<v><^>>v<<^><>v^><<>vv<^><<^vv>v<<<v^v><^<vv>>^>>^>><^vv<<^<><>>^<vv^><vv^v>>^^<^<^v^v><v><v>^<^vv>v<^><<<><<v>v^<<<^<<^><v<>^vvv^v^v>v^>v<v^v<^^<^>^^v>>v>v^<>vv<vv<><v<>^v<v^<>^>^v<v>>v^^^<v^^v^<>v^^v>^><^<^<<v^<<^<<vv>>v<^>^^<^>>>^<>vv<v>v^><<<v>v^>^><>>><^>v<<^<v<>v>><<>^<v>><^^v<v>v>vv^>v<^^<<<>>^v^^>^^<<v^v>>vv^><^^v>^>^<<v^>^^>vv^>vv^><^><<<vv><>v>^^^^>>^<>v>>v>><^^>v^vvvv^^>>vv^<v^^><vv>>v><>^^^<^^<<>vv^>v<<>v><>>><^vvvv^>^<>v^v<" +
            "v^vv<^v>^<<<>^vv^<^>v^>v>>>^^>>^<>v><<^<>><^^>v<v>^v^>>v^>>><><<><v><<><<v^v<>^v<<vv><<vv^^><>>vvv><>><>v<v^<v^>>^><><>^v<vv<>vvv>v>v<<><>>>^<>>^>v>>>>^<^^><<vvv<vv<^v<v<v<v>v^^><>vv>>^^><><><<^v<^<>>>^vv<<>^>^><^<v>^<v^<<^>>>v<>v<>v^^>v<vv<>>^>^>><v<<<>v><v>^><vvv<v^>v^>v><^vv><<<^^<<^<^<v^>^<^vv^<>>v<><>^<<><^^>^><v>v^vv^^<<<>v>v>^<<v>v>>v^<<>^>v^<^<><<^<>^v<v><v^>^vvv^><<vv^vvvvv^<>>^<^^v>>^v<<>>v<^>^^><vvv<v^v^<^v^<<<v<<><v^<<v<^v<v^>^>^<^<>^><<^^>vv<^<^v^v<><^^^v^^<><<^^>v>^<<^>>>>v>v^<>>>vvv>>v^<>^<vv^><v>><<<>>v<^<v>^<<>vv^<>vvv<>v><>v<v^>^^v>v^>v<>vvv>>><^vv<>^>><<<><^>^^<<^^^>>vvvvv^>^^><<<vv<^vv<<^<<>v><^><><>vv^vv^v>vv^v><^><><>v<^^v^v<><>^v<vv<v<^>^><>^^>>vv<>>vv>vv^<v^>^v>^<vvv>>v^v><>v<v>^<^<^vv>^v<><v<<<<vv>^^>^>v^<<<^>^^^<<v>>vvv>>v^>><^^^>>>v^^<><>v><>v<v^vv^><<<^^><^vv^^v^>><<^<>>>v^^>^<v<>>>vv^<^<vv^^<v>>vv>^vv^vv<^>>>v><v>^>^>^vv>>><>v<<^><<^>><>v^v>v<>^v^^>v<vv>v><>v>^><<<>v^<vvv>^<>><^^<vvvvvvv^v<<v^^<>^vv<>v^vv<>^><^^>^^>^<^>>>vvv<^^^v<<^<<><>^>vv<>^<<>>v>>^>vv>v" +
            "^<>v<>v><v<>v^<>v^^^^vvvvv<v>^v>vv^v^v^^v^^^^>v^v^>>v<>v^^<v><>>^<^^<vv>^>v<^>vvv><v^v^vv^>>^<<>>>^<>^v><v>^^v^><<<<v<v^v^^<>v^<>>v>v^>^>>><^>vv<>v<<>v^>>vv>v>^>><^><v<><^v^><^^<^>>^<^<v^>>v^<<>><>>><v^<>>v<vv>vv<>^v^vvv<vvv^<v<^>^v<>^v^^^^>>>v<v^<><v>v<>>v^>v>v><<<^^>>>v<^v^<<^v^<vv><<><>^v^^>^vv<>v^>v>^^<^v<^<^vv<><v^^<^>v^vv<<<<>>^v^<v<<>>v<vv<<^vv>^^>^^^<<<^<><vv^vv^>v>^vv<v>v<^^<vv^><>>v<vvvv^>^>>^^^<>><^^^<v<>^v>>>^>v^v^v<v>vv>^v<>^><<>>^v^<v<<>^>^<<^v<<v^v^v^^><v^^v>>>v>^vv<^<v<>vv^v^><^^^<><<^<>^^>^vv><<<<<^v^<^v<>vvv^v><vv<<>v^><v^>v>v^v^<>^^<>v>v^v<<><<<>^^v^>^v>v^^<<<vv>^v^>>^v<>>><v<vv^<>><^^<<v<>^^^<>>vv>^^v>^v<^>><>v>^<<<v^v>vv<^<<v<^^>v<v<vv^^v<><>^v^^<v^^<^vv^<v><^vv<^<vv^>>>^vvv<><^<^>v>^vv<<vv><<>^^>v>>^v<v>><^^vv^>^vvvv^v^<^>v<>^><^^^<<vv>>>v><><>^v<^v^^>^<v^<>vv<vv><>vv>>>>><<<<v><><>>^<^<<<>vvv<<<<>><>v^v>>^<>vv<^v^vv^^^v>>vv>^>^<^<<v^v>vvvv^vv^^>>^v^>^v><><vv><^<>v<>>v^^>^<v^>><v^<v>><>^^<vv^<<><>>vvv^v^>>^^<<^<<<^<>v^^^^^<<^>^^<>><><v>>><v^v<^>>^<>>>>>>v^vv<^>><v" +
            "v^>>>v<<v<<>>^v>>v<<>^^v<>v^^v<^>><>>^^>v^^<^>vvv>vv>v^v^>^>>v^>vv^>v<<vvvv>vvv>v<v<><^><v<>^v>><>v^<<>v^<v^^^<v>^^>><^><>^<^>>^v><vvv<<>^v<v>^<^><v<<^v<^<<<^^v<<<<<<^><>>>>^v>v<>^>^^<^<<>>^^^^<v>v>^>vvv<<<v>>^>v<<^^^vv>>>v><^^v<>>v^<v<^><v<<<vv^<vv<^^^^^v<^^>v^>>>^^^><<^^<<>>><<<<v<^^<v<^^<<>><<>v<>>^<><<<^>><>><<v<>><v>^v>^v<<<><v^<>^^v<<^v><vv^>v>>^^^v^^^vv^vv>^v^v^^^v<^>v<<<v^<^vvv<><vv>vv<>vv^^><v>>>>><<v<v>v>v^v<>>^><<<^<<>>^^<<>^^vv<^>>^>>>>><v<v>><v^vv<v^<<v<>v>v>>^>^^^><vvv>>^<<^^>>v<<^<<<v^v^v^^>v>^^<><^<><v><><><vv><v^v<^<^>^^v<>>><^<^<^<^v<^^v><^<^>v<>^<><^>^>>^^^v<vvv^<>vv^^>^>^<vv>^^>^>v><^><v<v<v^vv>^>^^v^>^<^><>><<v<>v^v<><<v^^>v<>vvv^^^<v<><<>^^^v^<<vv^v>>>v^<^><vv^<>^<>^<><>^^<><<v^vv>vv><<<v^<v<<>v>^^>v^>vv<>^<v<^vvv^>>vv>^>>^>^<^vv>>vvvv>vv^<^<v>>>^vv>vvv>^>v><vv^>v<<><vv>><>v<v<>v<<v>^<^><^^>^<vvv>><^v><^>>v<<v<v<<^><^>>vv<^>v>><^v^<>v><^^<><v^>^vv<<>>><><<>^><vv<^<>v^^<^^v^>^><^<^><>^<>>>>^>>vv>>vv^<<^vv>v^<^v><<>vvv>v>>vv>^^^<^v<<<>v<><>>vvv^<><vvv>>>^^><v<^>v<^^" +
            "v>^^>v<<>^<<<^^^^^>v><>^>>^<v><<<>>v<^><<^<<vv<vv<v^^v<>>>>^v<v>>v^^^^v<>^vvv><v>vv<v<v<<>v><>^^vv><<^v>v^>vvv><>><><^^vvv<v^<v^^><v>>>>^^><^^^<v^>>>><>vv<<>vv<>v^v<v>vv><<^^v^>v<><v^<^^vv<^<^^>v>v<v<vv^<<><v^v><vv^>^^vv^v>>>vv<^vv><vv><^><v<<^>^^^>>v^<v^<<^^^v^<<^<^^^^><<<vv<vv^>^vv^>><<v<>^vv>v^v<>><<>vv^>v>v^>><<v^>^^<v<^v<^vv>>v^^^>^v<^^v<v>^><vv>v^^^<^^^^<^><>vv<>^^>vv^v^><^^><v>^^vvv<>^^>v^v^<^^><>>>^^v^<^^v>vvv>^vvv<^vv>v<v^v><vv^>vv<<><>>^><>^<^>v<<<<><^v>>>v>v^^v><><v<^<<>^v<vv<<<v<v<^^^^<^^^<>v<<><><<<<<<^^><<<v<>^<<vv^^><<>^v>^><>>vv^>><<v<><>>>vv>vv<<vv<v>^<<^<^>v<<v<><<>^<<v^<vvv<^v><^<<<<>>>><^^<^^<^>>v>^^^>v<<^vv<^^vv<<v<<v>v<^><^vv^^>vv^<v><vv^v<>v^<^^^v>>>>vvvv<^v<<<<v<>^<v<<v>>v<v^<><^<>>^>^<>^<>vv<<><>^><<vv<>>vv<<>^^^vv>v^<<^v<<<>><vv<<<v^<v<v^^>>v<v^v<^<<><><v<v><<<v<>><><<>>^><><<<>^>>>^<<^<^^v<v^^><>>v<vv>>>v<<^><><<^^v>v<^<^<<>v<v>^v^v><>>vv<<^v^v<>v><^<<v^^^><<^vv>^>><^vvv>^<<<^v>^>>v<<<^v<^>^v<<^v^<vv<v>^<v^v>^^^v^><<>^vv^^^v<<v<v<v<v^<<v>>vv>>^<>>^<>v^>>^v^><" +
            "^^<v<v<<v>>v<^^vv<><^^<>^<v>v<^>><^^<><>^><^<^v<v^^^^v^>^^v<^>^v>v>vv>^^>v>v>^^^^v>v^v^v>^><v>>^^^>>v<^<>^^v<v^^>v<^^>><>>^vvv>^vvv>vv<<v><^v^<v<>>>^^^v^<<^>>^^v^^>v^^>^>^<v>^><>v>v<<v<>>v^^v<v^^>^>^<>><>><v^v^^^<>>^^>><<^>><<v>><<>>>>^^>>^v><^<>^>vv^v<><^^<^>^v<v^<<v^v^v>><^<vvv>^>^<<<<<^^v<^>>^><>>v>>>vvv>vvv><v<^^v>^v<<<^vv^^vv^<^^v><^<>^v<^^v^<v^><>v>^^^^>>>v>^>^^v<^<<>^<v^>><v<^v<vvv><>^><v>><>v>v>^<><>^v^<<^<<^<>v<^<v><v>^^v^<><>>v>v^<<<v>v<vv><v>v>><v<<>vv^>>v><<^^>>^^>>^^^v<<^v><>v^^v<^vv^>v<<>v><<<>^v>v<v^^<<^><v><>^^^^>>v>>vv<^v>>v^v<<<v<<^vvv^>><>v>v<>^<^v<>>>>^^^<>v<<<<^>vvv^><^<vvvv<<>v<v^<>^^<v>>^^>>^>><^vvv>v^^<<>><v<vv<v><^<v>v<v^<>v^vv^<>^>>^<>><^^^>^<>v>^vvv>>>>v^vv><<vv<v>^<<<>v<>>^>><v^v^v^<vv^<>^>v>^^v>^^>^v^^<^>^<><^<<<^^^vv>^<vvvv>>>v^><<><><<<^^<v<^^<^>>^>^^>vv<^^vvv<<>^>>><<v^^v>^vv<>>v<<<v^<>><<>><v^><>v>>^>^vv>><^v>v^v<v<v^^vv^<v<><vv<v>>vv><<<<<<v^v<<vvv<^^>>^><<<>^<^^<vv^>v^v<^^>>><<>>^v^>^^vv>^<>><v<^v><<<^v<<>vv>^vv^v^^>>^^^^<>v<>v>>^>>>>^>>>v^v>><<<<>v<^" +
            ">^>^<^v^<><v><<<<^><>^^<>>vv^v<^v>v<><<<^><^><^<v><^^<<>vv^<<<v^<>v^<^v<^<^vv<^<^^<<>>v><v>>^>>>^<^^<vvv>><vv^>v>v><<^v>>v>><><vv<>^<<>>vv^^^<v>><^<<v^>v<^<>v><vv<>v>^>><vv<>^^<^><><<<^>vv>>>><v<^v^<>><>vv<>^>^><<<><<v>v^>>><^v^^^<vv><<<>>v^><^><^>^<<^<<>^^^<<v<<v^<>v>^>>vv<<<^^^<<>><^<v^v<<^<<<<>^<^vv<><<^<>>^>>^^>^<<^vv<<v><<>>>^^^vv<v<<<^<^v>>v^>>>^v<^^>^>>>^v^vvv^<<>>><>><>v>^v>>v<^^><>><>>><<<vvv>^<v^v<^<^<v<<vv>^<vv>^<v>>v^<><^^<>><<v>v>><^v^>>>^>v<v<^<><vv^>^v>^^><<v<>>>v<>v>v^>v><v^<^v>>^^v>v<vv<v^v<>v^vv^v>^>vvv<>v>v<^vv>v^^^>^<v^^<^><v>v^><v<^>^v<>v>^v>^>><>><^<^>><^v<<><^>^v<>^v>v<v><^^v<<^v<^^^v<^<<><>v<>v^><>^^v<>^<vv<>vv^^<^^v<^<><<<^^><v>><vvv^^^v^v>>>^<>vvvv>^>vv^<><<v>><^v>v^^><<<v>><>^^<<^<>^^^^^<<<^><>^v^<v^v<<<<v^^<>^<<v<^<>v>><<^>^v^<^^<v^<<v^v<>^^v<v^v<^vv^^<>>v<<<^<><vv>v<^v>^>^^>v<^^>v>>><>v<<^^^v>^<>>><<>>^>>^<^^^<<^<<<vv>>vv^<^<v><<^vv^<<<v>v^^v<v>^<>^^^v<>v>vvv>^>^v<<>>^^^vv^>^>v^>>vv<<^>>vv<v>v>>>v<<^v><>^<<<>^v^vv^^vv><v^^<<>^^^v^^<^<v^^^<^v>vv>^>^<>v^>v^<>" +
            "^><<^^vvv><vv<v<^^><v<<<^<>v>v<^vvv<<<<>><<^>><<<>>><>v^>v^>>^vv<<>^>>>>^>>^><>^v><<>^^>^v><<^^vv><<v^v^<>^^v>v<^v^^^<^<^v>v<>>^>v^^<<>>v>>^^><^<^>^v^>vvvv>^v^vv>>v>v>^<^^>v^^<>>>^>v>^<>^<<^>^>^><><>^><>vv>><<>>>^v^^^<>^>>v^><^<v>vv^^<><<<v<<>^<>^<v><^^v>v^v^>>^<^v<v<<<<>^><<<vv><<<^>vv>v^^^^<^^^v><>v<>v>vv<v>>>><vvvv^<^^^<v<>><<^^<>v<<><>v<^>>v<>>^><<^v>v<><v<<<>><v^><^v^>^<<v^>v^v>><^^^<^<><>v<^^v>^v^<<<<^v<<^^^^v>v>^v^^<^vv<v<<<^>^v<<^>^>v<>^^^<<><v><>^v<><^<^<v<^v><v><^>v>^v<>^vv>^<v<^<vv>^^>vv<><>^<v^^>v<><<>v^v>><>>^<^^<^^^>^v^vv^vv^^<v<>^v>v><^vv^>>vv^<<v^^v>>>^<^v<>>^^^<><^v<vv>^<<<vv^^^<v>^^>vv^v><<v<<^v<v^><<^>v^^^<><>v^^v><v^^^v<^<>v><^<^v<^^^^v^^><>v<^^<v>^^<<^v<<v^v>v>v><>>^><^^vv^vv^<v>v<>vvvvvv><>>v<>^<v^>^>^v^>vv^<v<<^v<>^^^>^<><>^<<<>><>^v^<v^^<>vv<>^^<^^vvv^vv^v^^>><>^vvv><^vv>>>^>^>>>v<vvv><^>v^^^v<<>v<^v^<>^<^>>><<<>>vv^v>^v^v<^^>v^^>vv<^>vv^<^^>^^v><v<><v>>>>v>v^^^^^>^^v<^^>vvv<>^><>^^vv>v<vv^^><>v^^v>^^^>>v>>><>v>v<v<^>v^^<^^v^>v^^>v<^v^<vv>>>^vv>vv<<^<^vvvv<><>><" +
            ">v^v^<v^>^^^<^^v^<><v<v<<<vv<^^<^^^><v<>>v^>v<^<v^v^v<>v<^><^^v<v<vv^<<>>v<v><v^<>v<v><^^>vv><v^<>v>vv^><vv><>^<>>v<^^<>^^>v>v^^>vvvv<><>>v<<^><<^<<>>^<^^v^<^^vvv<v>^^>^>^>>v<v<v^<^><^^v<vv^<v<vv^>v<vv^^<^^<^^<<v>>^>^<>^><>v^v<<>>>^v^^vv><^<<<>v>^^<v^v<^v>>vvvv<<^vv^>^vv>^^>^<<><><>^>^<><><>^<><^^^v^>v<<>vv>v^^v><v^v>>>^v<^^v^vv>v>^>>^<>^>v<v^<^<v<>>v<<v^^>^v<v<^vv>^^v>^^<>v>v<^><v>v>><<<vvv>vv>^>^<^><>v><<>>^^<^v>v>v^v<<><v>v^v<vv^>^<<>>v<v^^<>>>vvv^><v>vv^v<>v^>v<v>><<>v<vv>vvvv^>>><>>v^><^<>>>v^v><^>>>^^>v^>^>v>><<>><<<>vv<^v><>^><vv><^<>v<<v>vvvvvv>>^^<>><>vv>v<vv>^v<>>v^^v^>v>><<^>v<^<v^v>v>>>>^><vv<^v<^^<vv<v^>^v<v^<v^vv^v<<>v<^<v^<<<<v^^><^v<<<v<^><v>v>vv<>>>vv<^>><^>>^<<>v<^>>><><^<><><>>>>>^>>v><^<v<<v^<<v<<>v^<^^^><v><vvv^>^>>^^^v<^>^<v<>><^>>>><^^^<^>^<v^^vv^>^v^<<v><>>^^<^><<^<^<<v<v>^>^>vv>>vv^^^^^>vvv^vv<^^<^>vv><>v>^<^^^v^><^v><^vvv<vv><^<v<v>^vv^<v><vvv<<<<v><>^<<v<^>vv^v>v<v^v^^vv^vv><<>>>>^>>^<v^vv>vvv>>^vv><^^><^<v<<v^^v^<v<<^>>^^>>v>^v>v<<<>><v><^v^>>>^>^><<v^>vvv<>").toCharArray();

    public static final char[] SMALLEX = "<^^>>>vv<v>>v<<".toCharArray();
    public static final char[] BIGGEX = ("<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^" +
            "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v" +
            "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<" +
            "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^" +
            "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><" +
            "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^" +
            ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^" +
            "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>" +
            "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>" +
            "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^").toCharArray();

    public static final char FREE = '.';
    public static final char ROBOT = '@';
    public static final char CRATE = 'O';
    public static final char CRATELEFT = '[';
    public static final char CRATERIGHT = ']';
    public static final char WALL = '#';
    public static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static final String DIRSTR = "><v^"; // maps to DIRECTIONS with same index

    public static int row = 0; // current robot coordinates
    public static int col = 0;

    public static void main(String[] args) {
        String fileName = "/home/jirka/src/java0/aoc24_15up.txt";
        char[][] map = AOCHelper.readFile2CharArray(fileName);
        int findStart = findPos(map, ROBOT);
        char[] INPUT = MOVES;
        row = findStart / 100;
        col = findStart % 100;
        System.out.printf("Read map of size %d, robot starts at [%d,%d]%n", map.length, row, col);
        for (char c: INPUT)
            doMove(map, c);
        AOCHelper.printField(map, "Result");
        long sum = 0;
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == CRATE)
                    sum += 100 * r + c;
        System.out.println("Part 1: " + sum);
        // 347421 -- too low

        /*********** PART 2 *******************/
        // read map again, as it changed
        map = AOCHelper.readFile2CharArray(fileName);
        // create a new map twice the width
        char[][] p2map = new char[map.length][2 * map[0].length];
        // copy and update content:
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map.length; c++) {
                p2map[r][2 * c] = map[r][c];
                p2map[r][2 * c + 1] = map[r][c];
                if (map[r][c] == ROBOT) p2map[r][2 * c + 1] = FREE;
                if (map[r][c] == CRATE) {
                    p2map[r][2 * c] = CRATELEFT;
                    p2map[r][2 * c + 1] = CRATERIGHT;
                }
            }
        AOCHelper.printField(p2map, "Start of P2");
        // find our robot, should be position Yx2X
        findStart = findPos(p2map, ROBOT);
        row = findStart / 100;
        col = findStart % 100;
        System.out.printf("P2 Read map of size %dx%d, robot starts at [%d,%d]%n", p2map.length, p2map[0].length, row, col);

        for (char c: INPUT)
            if (c == '<' || c == '>')
                doMove(p2map, c);
            else
                doMoveP2(p2map, c);
        AOCHelper.printField(p2map, "Result");
        sum = 0;
        for (int r = 0; r < p2map.length; r++)
            for (int c = 0; c < p2map[r].length; c++)
                if (p2map[r][c] == CRATELEFT)
                    sum += 100 * r + c;
        System.out.println("Part 2: " + sum);
    }

    public static int findPos(char[][] map, char target) {
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map[r].length; c++)
                if (map[r][c] == target)
                    return 100 * r + c;
        return -1;
    }

    public static void doMove(char[][] map, char dir) {
        // find dx, dy
        int[] direction = DIRECTIONS[DIRSTR.indexOf(dir)];
        int dx = direction[0];
        int dy = direction[1];
        int currX = col;
        int currY = row;
        // go dir until wall or empty is found
        while (map[currY][currX] != WALL && map[currY][currX] != FREE) {
            currX += dx;
            currY += dy;
        }
        if (map[currY][currX] == WALL) return;
        // if empty, go back until robot is found and move
        dx = -dx;
        dy = -dy;
        while (map[currY][currX] != ROBOT) {
            map[currY][currX] = map[currY + dy][currX + dx];
            currX += dx;
            currY += dy;
        }
        // update robot and pos:
        map[currY][currX] = FREE;
        row = currY - dy;
        col = currX - dx;
    }

    public static void doMoveP2(char[][] map, char dir) {
        // find dx, dy
        int[] direction = DIRECTIONS[DIRSTR.indexOf(dir)];
        int dx = direction[0];
        if (dx != 0) {
            System.out.println("Did not expect to move in X direction in P2?!");
            return;
        }
        int dy = direction[1];
        int currX = col;
        int currY = row;
        List<Integer> indices = new ArrayList<>();
        indices.add(col);
        boolean resolved = false;
        while (!resolved) {
            // move by dy
            currY += dy;
            // check all indices from the list
            List<Integer> next = new ArrayList<>();
            for (int idx: indices) {
                // b) any wall there is, no move at all
                if (map[currY][idx] == WALL) return; // no moving at all
                if (map[currY][idx] == CRATELEFT) {
                    next.add(idx);
                    next.add(idx + 1);
                }
                if (map[currY][idx] == CRATERIGHT) {
                    next.add(idx);
                    next.add(idx - 1);
                }

            }
            // a) everything free, we can move back
            // if I am here and next is empty, pull and go back
            // c) next step, if neither a nor b
            // otherwise, next step:

        }
        // go dir until wall or empty is found
        while (map[currY][currX] != WALL && map[currY][currX] != FREE) {
            currX += dx;
            currY += dy;
        }
        if (map[currY][currX] == WALL) return;
        // if empty, go back until robot is found and move
        dy = -dy;
        while (map[currY][currX] != ROBOT) {
            map[currY][currX] = map[currY + dy][currX + dx];
            currX += dx;
            currY += dy;
        }
        // update robot and pos:
        map[currY][currX] = FREE;
        row = currY - dy;
        col = currX - dx;
    }


}
