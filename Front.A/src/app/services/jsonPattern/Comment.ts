import {CommentData} from './CommentData';

export class Comment {
  _id: number;
  data: CommentData;
  parent: number;
  childs: number[];
}
