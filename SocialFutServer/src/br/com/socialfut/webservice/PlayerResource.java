package br.com.socialfut.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.socialfut.exception.NoContentException;
import br.com.socialfut.persistence.Player;

@Path("/player")
public class PlayerResource
{
    @GET
    @Path("/buscarTodos")
    @Produces("application/json")
    public List<Player> selTodos()
    {
        return PlayerWS.getPlayerWSInstance().getAllPlayers();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Player Player(@PathParam("id") int id)
    {
        Player player = br.com.socialfut.webservice.PlayerWS.getPlayerWSInstance().buscar(id);

        if (player == null)
        {
            throw new NoContentException("Player not found!");
        }
        return player;
    }

    // @POST
    // @Path("/inserir")
    // @Produces("application/json")
    // @Consumes("application/json")
    // public String insert(@PathParam("facebookId") long facebookId,
    // @PathParam("deviceRegId") String deviceRegId)
    // {
    // return "Cliente inserido no banco com sucesso!";
    // return PlayerWS.getPlayerWSInstance().insert(facebookId,deviceRegId);
    // }

    // @GET
    // @Path("/delete/{id}")
    // @Produces("application/json")
    // public String deleteCliente(@PathParam("id") int id)
    // {
    // return Banco.getBancoInstance().delete(id);
    // }

    // @GET
    // @Path("/buscarTodosGSON")
    // @Produces("application/json")
    // public String selTodosGSON()
    // {
    // return new Gson().toJson(Banco.getBancoInstance().getListaClientes());
    // }

}
