package main;

import java.io.Serializable;

public class Main implements Serializable {

	private static final long serialVersionUID = 1;

	public static void main(String[] args) {

		GameLogic.startGame();

//		String content, term = "";
//		String[] filter = { "Map:", "Room:", "Loc:", "Desc:", "Threat:", "Direction:", "Tips:" };
//
//		List<String> map = new ArrayList<>();
//		List<String> loc = new ArrayList<>();
//		List<String> threat = new ArrayList<>();
//		List<Integer> room = new ArrayList<>();
//		List<String> desc = new ArrayList<>();
//		List<String> direction = new ArrayList<>();
//		List<String> tips = new ArrayList<>();
//
//		try (BufferedReader read = new BufferedReader(
//				new FileReader("C:\\Users\\QCU\\Desktop\\ConsoleTypeShit\\src\\storyline\\Storyline.txt"))) {
//			while ((content = read.readLine()) != null) {
//				boolean trim = false;
//				for (String word : filter) {
//					if (content.trim().startsWith(word)) {
//						term = word;
//						trim = true;
//						break;
//					}
//				}
//
//				if (trim) {
//					int index = content.indexOf(":");
//					String data = content.substring(index + 1).trim();
//					
//					switch (term) {
//					case "Map:":
//						map.add(data);
//						break;
//
//					case "Room:":
//						room.add(Integer.parseInt(data));
//						break;
//
//					case "Loc:":
//						loc.add(data);
//						break;
//
//					case "Desc:":
//						desc.add(data);
//						break;
//
//					case "Threat:":
//						threat.add(data);
//						break;
//
//					case "Direction:":
//						direction.add(data);
//						break;
//
//					case "Tips:":
//						tips.add(data);
//						break;
//
//					default:
//						break;
//					}
//
//				}
//
//			}
//
//			System.out.println(map.toString());
//			System.out.println(room.toString());
//			System.out.println(loc.toString());
//			System.out.println(desc.toString());
//			System.out.println(threat.toString());
//			System.out.println(direction.toString());
//			System.out.println(tips.toString());
//
//		} catch (Exception e) {
//
//		}

	}

}
