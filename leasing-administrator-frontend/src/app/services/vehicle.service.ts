import {Injectable} from '@angular/core';
import {Constants} from "../constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Vehicle} from "../dto/vehicle";

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private readonly VEHICLE_API = Constants.BACKEND_URL + '/vehicles'

  constructor(private http: HttpClient) {
  }

  createVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(this.VEHICLE_API, vehicle);
  }

  updateVehicle(vehicle: Vehicle): Observable<void> {
    return this.http.put<void>(this.VEHICLE_API, vehicle);
  }

  getVehicleList(): Observable<Array<Vehicle>> {
    return this.http.get<Array<Vehicle>>(this.VEHICLE_API);
  }

  getVehiclesWithoutContract(): Observable<Array<Vehicle>> {
    return this.http.get<Array<Vehicle>>(this.VEHICLE_API + '?withoutContract=1');
  }
}
