import {Room} from './room';

export class Floor {
  public floorId: number;
  public floorName: string;
  public area: number;
  public cube: number;
  public light: number;
  public heating: number;
  public roomDtoList: Room[];
}
