package ermilov.focusstarttestovoe.apiCb

import ermilov.focusstarttestovoe.model.Valute
import ermilov.focusstarttestovoe.model.ValuteModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CbrApi {
    @GET("daily_json.js")
    suspend fun getallValute() : Response<ValuteModel>
}