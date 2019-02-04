import { VehicleReservation } from './../app/vehicleReservation';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  constructor(private http: HttpClient) { }

  getAllById(id): Observable<any> {
    return this.http.get("//localhost:8080/api/reservation/getAll/"+id);
  }

  getAllByUserId(id): Observable<any> {
    return this.http.get("//localhost:8080/api/reservation/getAllByUserId/"+id);
  }

  cancel(id): Observable<any> {
    return this.http.delete("//localhost:8080/api/reservation/cancel/"+id);
  }

  checkDate(date1,date2,id): Observable<any> {
    return this.http.get("//localhost:8080/api/reservation/checkDate/"+date1+"/"+date2+"/"+id);//rent a car id je id
  }

  book(id,reservation): Observable<any> {
    return this.http.post("//localhost:8080/api/reservation/book/" +id,reservation)
  }

  getAllByDate(date1,date2,id): Observable<any> {
    return this.http.get("//localhost:8080/api/reservation/getAllByDate/"+date1+"/"+date2+"/"+id);
  }
}
