import {Component, OnInit} from '@angular/core';
import {LeasingContract} from "../../dto/leasing-contract";
import {LeasingContractService} from "../../services/leasing-contract.service";

@Component({
  selector: 'app-leasing-contract-list',
  templateUrl: './leasing-contract-list.component.html',
  styleUrls: ['./leasing-contract-list.component.scss']
})
export class LeasingContractListComponent implements OnInit{
  displayForm = false;
  selectedContract: LeasingContract = new LeasingContract();
  contracts: Array<LeasingContract> = new Array<LeasingContract>();
  mode: 'CREATE' | 'UPDATE';


  constructor(private leasingContractService: LeasingContractService) {
  }

  ngOnInit(): void {
    this.getListOfContracts();
  }

  createNewContract() {
    this.displayForm = true;
    this.mode = "CREATE";
    this.selectedContract = new LeasingContract();
  }

  getListOfContracts() {
    this.leasingContractService.getLeasingContractList().subscribe(res => {
      this.contracts = res;
    })
  }

  editContract(contract: LeasingContract) {
    contract = JSON.parse(JSON.stringify(contract));
    this.selectedContract = contract;
    this.mode = "UPDATE";
    this.displayForm = true;
  }

  onContractUpdate(contract: LeasingContract) {
    const oldContractIndex = this.contracts.findIndex(c => c.vehicle.id === contract.vehicle.id && c.customer.id === contract.customer.id);
    if (oldContractIndex < 0) {
      return;
    }
    this.contracts.splice(oldContractIndex, 1, contract);
  }

  onContractAdded(contract: LeasingContract) {
    this.contracts.splice(0, 0, contract);
  }
}
