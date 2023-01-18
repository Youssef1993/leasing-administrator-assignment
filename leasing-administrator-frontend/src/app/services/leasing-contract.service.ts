import {Injectable} from '@angular/core';
import {Constants} from "../constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LeasingContract} from "../dto/leasing-contract";

@Injectable({
  providedIn: 'root'
})
export class LeasingContractService {
  private readonly LEASING_CONTRACT_API = Constants.BACKEND_URL + '/leasing-contracts'

  constructor(private http: HttpClient) {
  }

  createLeasingContract(contract: LeasingContract): Observable<LeasingContract> {
    return this.http.post<LeasingContract>(this.LEASING_CONTRACT_API, contract);
  }

  updateLeasingContract(contract: LeasingContract): Observable<void> {
    return this.http.put<void>(this.LEASING_CONTRACT_API, contract);
  }

  getLeasingContractList(): Observable<Array<LeasingContract>> {
    return this.http.get<Array<LeasingContract>>(this.LEASING_CONTRACT_API);
  }
}
