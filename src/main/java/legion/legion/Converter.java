package legion.legion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Converter {

	private String JSONContent;
	private JsonObject JSONObj;
	private String teamName;

	public Converter(String teamName) {

		this.JSONContent = "";
		this.teamName = teamName;

	}

public Board convert(String jsonContent) {

		Board board = new Board();
		Gson gson = new GsonBuilder().create();

		Type listType = new TypeToken<ArrayList<EpicHeroesLeague>>() {
		}.getType();

		JsonObject jsonObject = gson.fromJson(jsonContent, JsonObject.class);

		
		System.out.println("\n\n\n\nGetBoard JSON :" + jsonObject.get("playerBoards"));
		
		List<EpicHeroesLeague> EpicHeroesLeagues = new Gson().fromJson(jsonObject.get("playerBoards"), listType);

		System.out.println(EpicHeroesLeagues.size());

		for (EpicHeroesLeague e : EpicHeroesLeagues) {
			if (e.getPlayerName().equals(this.teamName)) {
				//board.setMiagicBot(e);
			} else {
				//board.setAdversaire(e);
			}
		}

		board.setNbrTurnsLeft(Integer.parseInt(jsonObject.get("nbrTurnsLeft").toString()));

		System.out.println(board);

		return board;

	}

}
